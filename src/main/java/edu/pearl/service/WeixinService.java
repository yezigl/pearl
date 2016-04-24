/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orion.core.utils.HttpUtils;

import edu.pearl.dao.BonusDao;
import edu.pearl.dao.BonusHistoryDao;
import edu.pearl.dao.UserDao;
import edu.pearl.dao.WxAccessTokenDao;
import edu.pearl.entity.Bonus;
import edu.pearl.entity.BonusHistory;
import edu.pearl.entity.User;
import edu.pearl.entity.WxAccessToken;
import edu.pearl.model.BonusSource;
import edu.pearl.model.Constants;
import edu.pearl.model.QRAction;
import edu.pearl.model.Source;
import edu.pearl.model.WxMediaType;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@Service
public class WeixinService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    WxAccessTokenDao wxAccessTokenDao;
    @Resource
    UserDao userDao;
    @Resource
    BonusDao bonusDao;
    @Resource
    BonusHistoryDao bonusHistoryDao;

    private boolean isSuccess(JSONObject jsonObject) {
        return jsonObject != null && !jsonObject.containsKey("errcode");
    }

    public String ApiWithAccessToken(String url) {
        return url + "?access_token=" + getAccessToken();
    }

    public String getAccessToken() {
        List<WxAccessToken> list = wxAccessTokenDao.findAll();
        WxAccessToken token = null;
        if (!list.isEmpty()) {
            token = list.get(0);
            if (!token.isExpired()) {
                return token.getAccessToken();
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", Constants.APP_ID);
        params.put("secret", Constants.APP_SECRECT);
        String uri = params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        String ret = HttpUtils.get(Constants.WX_API_ACCESSTOKEN + "?" + uri);
        JSONObject jsonObject = JSON.parseObject(ret);
        if (isSuccess(jsonObject)) {
            if (token == null) {
                token = new WxAccessToken();
            }
            token.setAccessToken(jsonObject.getString("access_token"));
            token.setExpireTime(System.currentTimeMillis() + jsonObject.getLongValue("expires_in") * 1000);
            wxAccessTokenDao.save(token);
            return token.getAccessToken();
        } else {
            logger.error("get accessToken error, {}", jsonObject);
        }
        return null;
    }
    

    public String getQrcodeTicket(User user, QRAction action) {
        Map<String, Object> params = new HashMap<>();
        if (action == QRAction.QR_SCENE) {
            params.put("expire_seconds", 604800);
        }
        params.put("action_name", action.name());
        Map<String, Object> sceneId = new HashMap<>();
        sceneId.put("scene_id", user.getScene());
        Map<String, Object> scene = new HashMap<>();
        scene.put("scene", sceneId);
        params.put("action_info", scene);
        String content = JSON.toJSONString(params);
        logger.debug("param = {}", content);

        String ret = HttpUtils.post(ApiWithAccessToken(Constants.WX_API_QRCODE), content);
        JSONObject jsonObject = JSONObject.parseObject(ret);
        logger.debug("ret = {}", jsonObject);
        if (isSuccess(jsonObject)) {
            return jsonObject.getString("ticket");
        }
        return null;
    }

    public String getMediaId(WxMediaType type, byte[] bytes) {
        String url = ApiWithAccessToken(Constants.WX_API_MEDIAUPLOAD) + "&type=" + type.name().toLowerCase();
        String ret = HttpUtils.upload(url, null, "media", bytes);
        JSONObject jsonObject = JSONObject.parseObject(ret);
        logger.debug("ret = {}", jsonObject);
        if (isSuccess(jsonObject)) {
            return jsonObject.getString("media_id");
        }
        return null;
    }

    public User getUser(String openId) {
        User user = userDao.findByOpenId(openId);
        // TODO
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            user.genScene();
            user.setSubscribe(true);
            user.setSource(Source.WEIXIN);
            userDao.save(user);
        }
        
        return user;
    }
    
    public void updateUser(User user) {
        userDao.save(user);
    }
    
    public Bonus getBonus(User user) {
        Bonus bonus = bonusDao.findByUser(user);
        if (bonus == null) {
            bonus = new Bonus();
        }
        return bonus;
    }
    
    public void updateBonus(User user, BonusSource source, int amount) {
        if (user.getFromScene() > 0) {
            User fromUser = userDao.findByScene(user.getFromScene());
            if (fromUser != null) {
                Bonus bonus = bonusDao.findByUser(fromUser);
                if (bonus == null) {
                    bonus = new Bonus();
                    bonus.setUser(fromUser);
                }
                bonus.setAmount(bonus.getAmount() + amount);
                bonusDao.save(bonus);
                BonusHistory bonusHistory = new BonusHistory();
                bonusHistory.setAmount(amount);
                bonusHistory.setSource(source);
                bonusHistoryDao.save(bonusHistory);
            }
        }
    }
}

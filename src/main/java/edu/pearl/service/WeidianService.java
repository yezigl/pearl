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

import edu.pearl.dao.VdAccessTokenDao;
import edu.pearl.entity.VdAccessToken;
import edu.pearl.model.Constants;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月28日
 */
@Service
public class WeidianService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    VdAccessTokenDao vdAccessTokenDao;
    
    private boolean isSuccess(JSONObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }
        JSONObject status = jsonObject.getJSONObject("status");
        return status != null && status.getIntValue("status_code") == 0;
    }
    
    public String getAccessToken() {
        List<VdAccessToken> list = vdAccessTokenDao.findAll();
        VdAccessToken token = null;
        if (!list.isEmpty()) {
            token = list.get(0);
            if (!token.isExpired()) {
                return token.getAccessToken();
            }
        }
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appkey", Constants.VD_APP_KEY);
        params.put("secret", Constants.VD_APP_SECRET);
        String uri = params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));
        String ret = HttpUtils.get(Constants.VD_API_ACCESSTOKEN + "?" + uri);
        JSONObject jsonObject = JSON.parseObject(ret);
        if (isSuccess(jsonObject)) {
            if (token == null) {
                token = new VdAccessToken();
            }
            JSONObject result = jsonObject.getJSONObject("result");
            token.setAccessToken(result.getString("access_token"));
            token.setExpireTime(System.currentTimeMillis() + result.getLongValue("expires_in") * 1000);
            vdAccessTokenDao.save(token);
            return token.getAccessToken();
        } else {
            logger.error("get accessToken error, {}", jsonObject);
        }
        return null;
    }
}

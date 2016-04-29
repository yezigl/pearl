/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orion.core.utils.HttpUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import edu.pearl.entity.Bonus;
import edu.pearl.entity.User;
import edu.pearl.model.BonusSource;
import edu.pearl.model.Constants;
import edu.pearl.model.QRAction;
import edu.pearl.model.WxEvent;
import edu.pearl.model.WxEventKey;
import edu.pearl.model.WxMedia;
import edu.pearl.model.WxMediaType;
import edu.pearl.model.WxMessage;
import edu.pearl.model.WxMessageType;
import edu.pearl.service.CacheService;
import edu.pearl.service.WeixinService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月21日
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    static {
        xstream.processAnnotations(new Class<?>[] { WxMessage.class });
    }

    @Resource
    WeixinService weixinService;
    @Resource
    CacheService cacheService;
    
    Set<WxMessage> duplicate = new HashSet<>();

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String verify(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce,
            @RequestParam String echostr) {
        List<String> list = Arrays.asList(Constants.WX_TOKEN, timestamp, nonce);
        String string = list.stream().sorted().reduce("", String::concat);
        String sha1 = DigestUtils.sha1Hex(string);
        logger.info("signature:{}, sha1:{}, string:{}", signature, sha1, string);
        return echostr;
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public Object message(@RequestBody String body) {
        WxMessage response = new WxMessage();
        response.setFromUserName(Constants.WX_USER_ID);
        response.setCreateTime(System.currentTimeMillis() / 1000);

        logger.info("receive\n{}", body);
        WxMessage msg = (WxMessage) xstream.fromXML(body);
        if (duplicate.contains(msg)) {
            logger.warn("receive duplicate message");
            return "success";
        } else {
            duplicate.add(msg);
        }
        logger.info("format\n{}", msg);
        WxMessageType messageType = WxMessageType.valueOf(msg.getMsgType().toUpperCase());
        switch (messageType) {
        case EVENT:
            WxEvent event = WxEvent.valueOf(msg.getEvent().toUpperCase());
            processMessageEvent(msg, response, event);
            break;
        case IMAGE:
            break;
        case LINK:
            break;
        case LOCATION:
            break;
        case SHORTVIDEO:
            break;
        case TEXT:
            // response.setToUserName(msg.getFromUserName());
            // response.setFromUserName(Constants.USER_ID);
            // response.setMsgType(WxMessageType.TEXT.getType());
            // response.setCreateTime(System.currentTimeMillis() / 1000);
            // response.setContent("你发送的内容是：" + msg.getContent());
            break;
        case VIDEO:
            break;
        case VOICE:
            break;
        default:
            return "success";
        }
        if (response.getToUserName() == null) {
            return "success";
        }
        logger.info("send\n{}", xstream.toXML(response));

        return response;
    }

    private WxMessage processMessageEvent(WxMessage request, WxMessage response, WxEvent event) {
        User user = weixinService.getUser(request.getFromUserName());
        switch (event) {
        case CLICK:
            if (WxEventKey.QRCODE.equals(request.getEventKey())) {
                String mediaId = cacheService.get(user.getOpenId());
                if (mediaId == null) {
                    String ticket = weixinService.getQrcodeTicket(user, QRAction.QR_SCENE);
                    if (ticket != null) {
                        byte[] bytes = HttpUtils.getBytes(Constants.WX_API_SHOWQRCODE + ticket, null);
                        if (bytes != null) {
                            mediaId = weixinService.getMediaId(WxMediaType.IMAGE, bytes);
                            cacheService.set(user.getOpenId(), mediaId, 86400);
                        }
                    }
                }
                if (mediaId != null) {
                    response.setToUserName(request.getFromUserName());
                    response.setMsgType(WxMessageType.IMAGE.getType());
                    WxMedia media = new WxMedia();
                    media.setMediaId(mediaId);
                    response.setImage(media);
                }
            } else if (WxEventKey.BONUS.equals(request.getEventKey())) {
                Bonus bonus = weixinService.getBonus(user);
                response.setToUserName(request.getFromUserName());
                response.setMsgType(WxMessageType.TEXT.getType());
                response.setContent("您当前在珍珠教育的个人总积分为" + bonus.getAmount() + "分");
            } else if (WxEventKey.TODO.equals(request.getEventKey())) {
                response.setToUserName(request.getFromUserName());
                response.setMsgType(WxMessageType.TEXT.getType());
                response.setContent("敬请期待，感谢您的光临");
            }
            break;
        case SUBSCRIBE:
            int scene = 0;
            if (StringUtils.isNotBlank(request.getEventKey())) {
                scene = Integer.parseInt(request.getEventKey().replace("qrscene_", ""));
            }
            user.setFromScene(scene);
            user.setSubscribe(true);
            weixinService.updateUser(user);
            // TODO 只有新用户第一次才会加积分
            if (user.isNew()) {
                weixinService.updateBonus(user, BonusSource.SHARE, BonusSource.SHARE.score);
            }
            // TODO 发送一条消息
            break;
        case UNSUBSCRIBE:
            user.setSubscribe(false);
            weixinService.updateUser(user);
            break;
        case LOCATION:
        case VIEW:
        case SCAN:
        }
        return response;
    }
}

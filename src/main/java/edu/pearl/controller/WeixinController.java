/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
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

import edu.pearl.model.Constants;
import edu.pearl.model.QRAction;
import edu.pearl.model.WxEvent;
import edu.pearl.model.WxEventKey;
import edu.pearl.model.WxMedia;
import edu.pearl.model.WxMediaType;
import edu.pearl.model.WxMessage;
import edu.pearl.model.WxMessageType;
import edu.pearl.service.WeixinService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月21日
 */
@RestController
public class WeixinController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static XStream xstream = new XStream(new XppDriver(new NoNameCoder()));

    static {
        xstream.processAnnotations(new Class<?>[] { WxMessage.class });
    }
    
    @Resource
    WeixinService weixinService;
    
    Random random = new Random(Integer.MAX_VALUE);

    @RequestMapping(value = "/message/callback", method = RequestMethod.GET)
    public String verify(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce,
            @RequestParam String echostr) {
        List<String> list = Arrays.asList(timestamp, nonce, echostr);
        String string = list.stream().sorted().reduce("", String::concat);
        String sha1 = DigestUtils.sha1Hex(string);
        logger.info("signature:{}, sha1:{}, string:{}", signature, sha1, string);
        return echostr;
    }

    @RequestMapping(value = "/message/callback", method = RequestMethod.POST)
    public Object message(@RequestBody String body) {
        WxMessage wm = new WxMessage();

        logger.info("{}", body);
        WxMessage msg = (WxMessage) xstream.fromXML(body);
        logger.info("{}", msg);
        WxMessageType messageType = WxMessageType.valueOf(msg.getMsgType().toUpperCase());
        switch (messageType) {
        case EVENT:
            WxEvent event = WxEvent.valueOf(msg.getEvent());
            switch (event) {
            case CLICK:
                if (WxEventKey.QRCODE.equals(msg.getEventKey())) {
                    String ticket = weixinService.getQrcodeTicket(random.nextInt(), QRAction.QR_SCENE);
                    if (ticket != null) {
                        byte[] bytes = HttpUtils.getBytes(Constants.WX_API_SHOWQRCODE + ticket, null);
                        if (bytes != null) {
                            String mediaId = weixinService.getMediaId(WxMediaType.IMAGE, bytes);
                            if (mediaId != null) {
                                wm.setToUserName(msg.getFromUserName());
                                wm.setFromUserName(Constants.USER_ID);
                                wm.setMsgType(WxMessageType.IMAGE.getType());
                                wm.setCreateTime(System.currentTimeMillis() / 1000);
                                WxMedia media = new WxMedia();
                                media.setMediaId(mediaId);
                                wm.setImage(media);
                            }
                        }
                    }
                }
                break;
            case SUBSCRIBE:
                break;
            case LOCATION:
            case UNSUBSCRIBE:
            case VIEW:
            case SCAN:
            }
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
            wm.setToUserName(msg.getFromUserName());
            wm.setFromUserName(Constants.USER_ID);
            wm.setMsgType(WxMessageType.TEXT.getType());
            wm.setCreateTime(System.currentTimeMillis() / 1000);
            wm.setContent("你发送的内容是：" + msg.getContent());
            break;
        case VIDEO:
            break;
        case VOICE:
            break;
        default:
            return "success";
        }
        if (wm.getToUserName() == null) {
            return "success";
        }
        logger.info(xstream.toXML(wm));
        
        return wm;
    }
}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import edu.pearl.model.WxMessage;

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
    public String message(@RequestBody String body) {
        logger.info("{}", body);
        Object object = xstream.fromXML(body);
        logger.info("{}", object);
        return "success";
    }
}

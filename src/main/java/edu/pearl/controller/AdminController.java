/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.orion.core.utils.HttpUtils;

import edu.pearl.model.Button;
import edu.pearl.model.ButtonType;
import edu.pearl.model.Constants;
import edu.pearl.service.WeixinService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月22日
 */
@RestController
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    WeixinService weixinService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "ok";
    }

    @RequestMapping(value = "/menu/create", method = RequestMethod.GET)
    public Object menuCreate() {
        Map<String, List<Button>> buttons = genButtons();
        logger.info(JSON.toJSONString(buttons));
        String ret = HttpUtils.post(weixinService.ApiWithAccessToken(Constants.WX_API_MENUCREATE), JSON.toJSONString(buttons));
        return ret;
    }
    
    @RequestMapping(value = "/menu/delete", method = RequestMethod.GET)
    public Object menuDelete() {
        String ret = HttpUtils.get(weixinService.ApiWithAccessToken(Constants.WX_API_MENUDELETE));
        return ret;
    }
    
    private Map<String, List<Button>> genButtons() {
        Map<String, List<Button>> buttons = new HashMap<>();
        Button button1 = new Button();
        button1.setName("在线购买");
        Button subButton1_1 = new Button.Builder().type(ButtonType.VIEW.getType()).name("少儿英语")
                .url("http://weidian.com/item_classes.html?userid=205909905&c=77542586&des=%E5%B0%91%E5%84%BF%E8%8B%B1%E8%AF%AD&wfr=wechatpo_keywords_shop").build();
        Button subButton1_2 = new Button.Builder().type(ButtonType.VIEW.getType()).name("青少年英语")
                .url("http://weidian.com/item_classes.html?userid=205909905&c=77674898&des=%E9%9D%92%E5%B0%91%E5%B9%B4%E8%8B%B1%E8%AF%AD&wfr=wechatpo_keywords_shop").build();
        Button subButton1_3 = new Button.Builder().type(ButtonType.VIEW.getType()).name("成人英语")
                .url("http://weidian.com/item_classes.html?userid=205909905&c=77674914&des=%E6%88%90%E4%BA%BA%E8%8B%B1%E8%AF%AD&wfr=wechatpo_keywords_shop").build();
        Button subButton1_4 = new Button.Builder().type(ButtonType.VIEW.getType()).name("VIP课程")
                .url("http://weidian.com/item_classes.html?userid=205909905&c=77674959&des=%E5%AE%9A%E5%88%B6%E8%AF%BE%E7%A8%8B&wfr=wechatpo_keywords_shop").build();
        button1.setSubButton(Arrays.asList(subButton1_1, subButton1_2, subButton1_3, subButton1_4));
        Button button2 = new Button();
        button2.setName("二维码");
        Button subButton2_1 = new Button.Builder().type(ButtonType.CLICK.getType()).name("积分").key("bonus").build();
        Button subButton2_2 = new Button.Builder().type(ButtonType.CLICK.getType()).name("我的二维码").key("qrcode").build();
        button2.setSubButton(Arrays.asList(subButton2_1, subButton2_2));
        Button button3 = new Button();
        button3.setName("会员信息");
        Button subButton3_1 = new Button.Builder().type(ButtonType.CLICK.getType()).name("我的预约")
                .key("todo").build();
        button3.setSubButton(Arrays.asList(subButton3_1));
        List<Button> list = Arrays.asList(button1, button2, button3);
        buttons.put("button", list);
        
        return buttons;
    }
}

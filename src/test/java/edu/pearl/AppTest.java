/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import edu.pearl.model.Button;
import edu.pearl.model.ButtonType;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
public class AppTest {

    @Test
    public void test() {
        Map<String, List<Button>> buttons = new HashMap<>();
        Button button1 = new Button();
        button1.setName("在线购买");
        Button subButton1_1 = new Button.Builder().type(ButtonType.VIEW.getType()).name("少儿英语")
                .url("http://zhibo666.tv").build();
        Button subButton1_2 = new Button.Builder().type(ButtonType.VIEW.getType()).name("青少年英语")
                .url("http://zhibo666.tv").build();
        Button subButton1_3 = new Button.Builder().type(ButtonType.VIEW.getType()).name("成人英语")
                .url("http://zhibo666.tv").build();
        Button subButton1_4 = new Button.Builder().type(ButtonType.VIEW.getType()).name("VIP课程")
                .url("http://zhibo666.tv").build();
        button1.setSubButton(Arrays.asList(subButton1_1, subButton1_2, subButton1_3, subButton1_4));
        Button button2 = new Button();
        button2.setName("二维码");
        Button subButton2_1 = new Button.Builder().type(ButtonType.CLICK.getType()).name("积分").key("bonus").build();
        Button subButton2_2 = new Button.Builder().type(ButtonType.CLICK.getType()).name("我的二维码").url("qrcode").build();
        button2.setSubButton(Arrays.asList(subButton2_1, subButton2_2));
        Button button3 = new Button();
        button3.setName("会员信息");
        Button subButton3_1 = new Button.Builder().type(ButtonType.VIEW.getType()).name("我的预约")
                .url("http://zhibo666.tv").build();
        button3.setSubButton(Arrays.asList(subButton3_1));
        List<Button> list = Arrays.asList(button1, button2, button3);
        buttons.put("button", list);
        
        System.out.println(JSON.toJSONString(buttons, true));
    }
}

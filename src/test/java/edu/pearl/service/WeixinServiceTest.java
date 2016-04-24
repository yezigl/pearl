/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.service;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.orion.core.utils.HttpUtils;

import edu.pearl.TestConfig;
import edu.pearl.entity.User;
import edu.pearl.model.Constants;
import edu.pearl.model.QRAction;
import edu.pearl.model.WxMediaType;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class WeixinServiceTest {
    
    @Resource
    WeixinService weixinService;

    /**
     * Test method for {@link edu.pearl.service.WeixinService#getAccessToken()}.
     */
    @Test
    public void testGetAccessToken() {
        String token = weixinService.getAccessToken();
        System.out.println(token);
    }
    
    @Test
    public void testGetQrcodeTicket() {
        User user = new User();
        user.genScene();
        String ticket = weixinService.getQrcodeTicket(user, QRAction.QR_SCENE);
        System.out.println(ticket);
    }

    @Test
    public void testGetMediaId() throws IOException {
        String ticket = "gQHe7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL29UcGpTWnprbWl2andmQmRnUlFNAAIE_cIbVwMEgDoJAA==";
        System.out.println(Constants.WX_API_SHOWQRCODE + ticket);
        byte[] bytes = HttpUtils.getBytes(Constants.WX_API_SHOWQRCODE + ticket, null);
        if (bytes != null) {
            String mediaId = weixinService.getMediaId(WxMediaType.IMAGE, bytes);
            System.out.println(mediaId);
        }
    }
}

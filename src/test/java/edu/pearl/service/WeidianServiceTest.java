/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.pearl.TestConfig;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class WeidianServiceTest {

    @Resource
    WeidianService weidianService;
    
    /**
     * Test method for {@link edu.pearl.service.WeidianService#getAccessToken()}.
     */
    @Test
    public void testGetAccessToken() {
        String string = weidianService.getAccessToken();
        System.out.println(string);
    }

}

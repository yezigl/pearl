/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.pearl.service.WeidianService;
import edu.pearl.service.WeixinService;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@Component
public class AccessTokenTasker {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    WeixinService weixinService;
    @Resource
    WeidianService weidianService;

    @Scheduled(cron = "0 0 */2 * * ?")
    public void updateWxAccessToken() {
        logger.debug("update wx access token");
        weixinService.getAccessToken();
    }
    
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateVdAccessToken() {
        logger.debug("update vd access token");
        weidianService.getAccessToken();
    }
}

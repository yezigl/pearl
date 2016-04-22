/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@Component
public class AccessTokenTasker {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0 */2 * * ?")
    public void updateWxAccessToken() {
        logger.debug("update access token");
    }
}

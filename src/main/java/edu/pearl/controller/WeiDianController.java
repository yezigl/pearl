/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月28日
 */
@RestController
@RequestMapping("/weidian")
public class WeiDianController {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String get(@RequestBody String body) {
        logger.info("{}", body);
        return "success";
    }
    
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public String post(@RequestBody String body) {
        logger.info("{}", body);
        return "success";
    }
}

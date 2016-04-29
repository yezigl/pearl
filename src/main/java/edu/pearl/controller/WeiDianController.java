/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import edu.pearl.dao.VdOrderLogDao;
import edu.pearl.entity.VdOrderLog;

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
    
    @Resource
    VdOrderLogDao vdOrderLogDao;

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String get(@RequestBody String body) {
        logger.info("{}", body);
        return "success";
    }
    
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public String post(@RequestParam String traceID, @RequestParam String content) {
        logger.info("{}", JSON.parseObject(content));
        VdOrderLog log = new VdOrderLog();
        log.setTraceId(traceID);
        log.setContent(content);
        vdOrderLogDao.save(log);
        return "success";
    }
}

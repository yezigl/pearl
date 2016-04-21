/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package edu.pearl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * description here
 *
 * @author yezi
 * @since 2015年6月14日
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

}

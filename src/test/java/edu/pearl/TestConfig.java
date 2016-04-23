/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@EnableAutoConfiguration
@Configuration
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(value = { "edu.pearl.dao", "edu.pearl.service" })
public class TestConfig {

}

/**
 * Copyright 2015 yezi.gl. All Rights Reserved.
 */
package edu.pearl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoDataAutoConfiguration;

/**
 * description here
 *
 * @author lidehua
 * @since 2015年5月29日
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class PearlApplication {

    public static void main(String[] args) {
        SpringApplication.run(PearlApplication.class, args);
    }
}

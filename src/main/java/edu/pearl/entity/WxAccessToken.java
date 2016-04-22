/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

import com.orion.mongodb.entity.AbstractEntity;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@Entity("accesstoken")
@Indexes({ @Index(fields = { @Field("expireTime") }) })
public class WxAccessToken extends AbstractEntity {

    private String accessToken;
    private String expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

}

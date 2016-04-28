/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.orion.mongodb.entity.MongoEntity;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月28日
 */
@Document(collection = "vdaccesstoken")
public class VdAccessToken extends MongoEntity {
    
    private String accessToken;
    private long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }
}

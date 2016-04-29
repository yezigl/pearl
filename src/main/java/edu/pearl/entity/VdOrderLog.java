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
 * @since 2016年4月29日
 */
@Document(collection = "vdorderlog")
public class VdOrderLog extends MongoEntity {

    private String traceId;
    private String content;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
public enum WxMessageType {
    
    TEXT,
    IMAGE,
    VOICE,
    VIDEO,
    SHORTVIDEO,
    LOCATION,
    LINK;
    
    public boolean equals(String text) {
        return name().toLowerCase().equals(text);
    }

    /**
     * @return
     */
    public String getType() {
        return name().toLowerCase();
    }
    
}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
public enum WxEventKey {

    BONUS,
    QRCODE;
    
    public boolean equals(String text) {
        return name().toLowerCase().equals(text);
    }
}

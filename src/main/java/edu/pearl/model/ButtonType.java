/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月22日
 */
public enum ButtonType {

    CLICK("click"), VIEW("view");

    private String type;

    /**
     * 
     */
    private ButtonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}

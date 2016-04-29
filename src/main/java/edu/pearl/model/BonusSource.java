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
public enum BonusSource {

    SHARE(100);
    
    public int score;
    
    /**
     * 
     */
    private BonusSource(int score) {
        this.score = score;
    }
}

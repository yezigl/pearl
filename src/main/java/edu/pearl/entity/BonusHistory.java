/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.orion.mongodb.entity.MongoEntity;

import edu.pearl.model.BonusSource;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
@Document(collection = "bonushistory")
public class BonusHistory extends MongoEntity {

    @DBRef
    @Indexed
    private User user;
    private BonusSource source;
    private int num;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BonusSource getSource() {
        return source;
    }

    public void setSource(BonusSource source) {
        this.source = source;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}

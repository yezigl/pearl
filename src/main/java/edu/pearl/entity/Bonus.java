/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.orion.mongodb.entity.MongoEntity;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
@Document(collection = "bonus")
public class Bonus extends MongoEntity {

    @DBRef
    @Indexed
    private User user;
    private int amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

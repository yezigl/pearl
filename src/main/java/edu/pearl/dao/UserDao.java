/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.dao;

import com.orion.mongodb.dao.MongoDao;

import edu.pearl.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
public interface UserDao extends MongoDao<User> {

    public User findByOpenId(String openId);
    
    public User findByScene(int scene);
}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.dao;

import com.orion.mongodb.dao.MongoDao;

import edu.pearl.entity.Bonus;
import edu.pearl.entity.User;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
public interface BonusDao extends MongoDao<Bonus> {

    public Bonus findByUser(User user);
}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.entity;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.orion.mongodb.entity.MongoEntity;

import edu.pearl.model.Source;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
@Document(collection = "user")
public class User extends MongoEntity {

    @Indexed
    private String openId;
    private String unionId;
    private boolean subscribe;
    private String nickname;
    private String avatar;
    @Indexed
    private int scene;
    private int fromScene;
    private Source source;
    @Transient
    private boolean isNew;

    public void genScene() {
        scene = (int) (System.currentTimeMillis() / 1000);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }

    public int getFromScene() {
        return fromScene;
    }

    public void setFromScene(int fromScene) {
        this.fromScene = fromScene;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

}

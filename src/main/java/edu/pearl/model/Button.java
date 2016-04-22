/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月22日
 */
public class Button {

    @JsonInclude(Include.NON_NULL)
    private String type;
    @JsonInclude(Include.NON_NULL)
    private String name;
    @JsonInclude(Include.NON_NULL)
    private String key;
    @JsonInclude(Include.NON_NULL)
    private String url;
    @JSONField(name = "sub_button")
    @JsonInclude(Include.NON_NULL)
    private List<Button> subButton;
    
    public static class Builder {
        
        Button button;
        
        public Builder() {
            button = new Button();
        }
        
        public Builder type(String type) {
            button.setType(type);
            return this;
        }
        
        public Builder name(String name) {
            button.setName(name);
            return this;
        }
        
        public Builder key(String key) {
            button.setKey(key);
            return this;
        }
        
        public Builder url(String url) {
            button.setUrl(url);
            return this;
        }
        
        public Button build() {
            return button;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Button> getSubButton() {
        return subButton;
    }

    public void setSubButton(List<Button> subButton) {
        this.subButton = subButton;
    }

}

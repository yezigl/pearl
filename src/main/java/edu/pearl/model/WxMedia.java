/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月24日
 */
public class WxMedia {

    private String MediaId;

    @XmlElement(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}

/**
 * Copyright 2016 yezi.gl. All Rights Reserved.
 */
package edu.pearl.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * description here
 *
 * @author yezi
 * @since 2016年4月23日
 */
@XStreamAlias("xml")
@XmlRootElement(name = "xml")
public class WxMessage {

    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private Long MsgId;
    private String PicUrl;
    private String MediaId;
    private String Format;
    private String Recognition;
    private String ThumbMediaId;
    private Float Location_X;
    private Float Location_Y;
    private Integer Scale;
    private String Label;
    private String Title;
    private String Description;
    private String Url;
    private String Event;
    private String EventKey;
    private String Ticket;
    private Float Latitude;
    private Float Longitude;
    private Float Precision;
    private WxMedia Image;
    private Integer MenuId;

    @XmlElement(name = "ToUserName")
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    @XmlElement(name = "CreateTime")
    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    @XmlElement(name = "MsgType")
    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    @XmlElement(name = "Content")
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @XmlElement(name = "MsgId")
    public Long getMsgId() {
        return MsgId;
    }

    public void setMsgId(Long msgId) {
        MsgId = msgId;
    }

    @XmlElement(name = "PicUrl")
    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    @XmlElement(name = "MediaId")
    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    @XmlElement(name = "Format")
    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    @XmlElement(name = "Recognition")
    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }

    @XmlElement(name = "ThumbMediaId")
    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

    @XmlElement(name = "Location_X")
    public Float getLocation_X() {
        return Location_X;
    }

    public void setLocation_X(Float location_X) {
        Location_X = location_X;
    }

    @XmlElement(name = "Location_Y")
    public Float getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(Float location_Y) {
        Location_Y = location_Y;
    }

    @XmlElement(name = "Scale")
    public Integer getScale() {
        return Scale;
    }

    public void setScale(Integer scale) {
        Scale = scale;
    }

    @XmlElement(name = "Label")
    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    @XmlElement(name = "Title")
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @XmlElement(name = "Description")
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @XmlElement(name = "Url")
    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @XmlElement(name = "Event")
    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    @XmlElement(name = "EventKey")
    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    @XmlElement(name = "Ticket")
    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    @XmlElement(name = "Latitude")
    public Float getLatitude() {
        return Latitude;
    }

    public void setLatitude(Float latitude) {
        Latitude = latitude;
    }

    @XmlElement(name = "Longitude")
    public Float getLongitude() {
        return Longitude;
    }

    public void setLongitude(Float longitude) {
        Longitude = longitude;
    }

    @XmlElement(name = "Precision")
    public Float getPrecision() {
        return Precision;
    }

    public void setPrecision(Float precision) {
        Precision = precision;
    }

    @XmlElement(name = "Image")
    public WxMedia getImage() {
        return Image;
    }

    public void setImage(WxMedia image) {
        Image = image;
    }

    @XmlElement(name = "MenuId")
    public Integer getMenuId() {
        return MenuId;
    }

    public void setMenuId(Integer menuId) {
        MenuId = menuId;
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE);
        builder.append("ToUserName", ToUserName);
        builder.append("FromUserName", FromUserName);
        builder.append("CreateTime", CreateTime);
        builder.append("MsgType", MsgType);
        if (MsgId != null) {
            builder.append("MsgId", MsgId);
        }
        if (Content != null) {
            builder.append("Content", Content);
        }
        if (PicUrl != null) {
            builder.append("PicUrl", PicUrl);
        }
        if (MediaId != null) {
            builder.append("MediaId", MediaId);
        }
        if (Format != null) {
            builder.append("Format", Format);
        }
        if (Recognition != null) {
            builder.append("Recognition", Recognition);
        }
        if (ThumbMediaId != null) {
            builder.append("ThumbMediaId", ThumbMediaId);
        }
        if (Location_X != null) {
            builder.append("Location_X", Location_X);
        }
        if (Location_Y != null) {
            builder.append("Location_Y", Location_Y);
        }
        if (Scale != null) {
            builder.append("Scale", Scale);
        }
        if (Label != null) {
            builder.append("Label", Label);
        }
        if (Title != null) {
            builder.append("Title", Title);
        }
        if (Description != null) {
            builder.append("Description", Description);
        }
        if (Url != null) {
            builder.append("Url", Url);
        }
        if (Event != null) {
            builder.append("Event", Event);
        }
        if (EventKey != null) {
            builder.append("EventKey", EventKey);
        }
        if (Ticket != null) {
            builder.append("Ticket", Ticket);
        }
        if (Latitude != null) {
            builder.append("Latitude", Latitude);
        }
        if (Longitude != null) {
            builder.append("Longitude", Longitude);
        }
        if (Precision != null) {
            builder.append("Precision", Precision);
        }
        if (MenuId != null) {
            builder.append("MenuId", MenuId);
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        if (MsgId != null) {
            result = prime * result + MsgId.hashCode();
        } else {
            result = prime * result + (int) (CreateTime ^ (CreateTime >>> 32));
            result = prime * result + ((FromUserName == null) ? 0 : FromUserName.hashCode());
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WxMessage other = (WxMessage) obj;
        if (MsgId != null) {
            if (!MsgId.equals(other.getMsgId())) {
                return false;
            }
        } else {
            if (CreateTime != other.CreateTime)
                return false;
            if (FromUserName == null) {
                if (other.FromUserName != null)
                    return false;
            } else if (!FromUserName.equals(other.FromUserName))
                return false;
        }
        return true;
    }

}

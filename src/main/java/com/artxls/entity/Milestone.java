package com.artxls.entity;

import java.io.Serializable;
import java.util.Date;

public class Milestone implements Serializable {
    private Integer id;

    private Integer infoId;

    private Integer myear;

    private String introduction;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public Integer getMyear() {
        return myear;
    }

    public void setMyear(Integer myear) {
        this.myear = myear;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
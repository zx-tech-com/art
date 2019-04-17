package com.artxls.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Photo implements Serializable {
    private Integer id;

    private Integer infoId;

    private String wname;

    private Integer wtype;

    private Integer subtype;

    private String url;

    private Integer bginYear;

    private Integer endYear;

    private BigDecimal width;

    private BigDecimal height;

    private Date createTime;

    private Integer iindex;

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

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname == null ? null : wname.trim();
    }

    public Integer getWtype() {
        return wtype;
    }

    public void setWtype(Integer wtype) {
        this.wtype = wtype;
    }

    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getBginYear() {
        return bginYear;
    }

    public void setBginYear(Integer bginYear) {
        this.bginYear = bginYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIindex() {
        return iindex;
    }

    public void setIindex(Integer iindex) {
        this.iindex = iindex;
    }
}
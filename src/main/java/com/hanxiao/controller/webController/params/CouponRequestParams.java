package com.hanxiao.controller.webController.params;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by wenzhi on 17/9/12.
 */
public class CouponRequestParams {
    private String type;
    private String description;//推广文案
    private double originPrice;
    private double couponPrice;
    private double couponValue = originPrice - couponPrice;
    private String imgUrl;
    private String actionUrl;//跳转链
    private boolean hasTicket;

    private String platFormImg;
    private String platformDesc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public double getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(double couponValue) {
        this.couponValue = couponValue;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public boolean isHasTicket() {
        return hasTicket;
    }

    public void setHasTicket(boolean hasTicket) {
        this.hasTicket = hasTicket;
    }

    public String getPlatFormImg() {
        return platFormImg;
    }

    public void setPlatFormImg(String platFormImg) {
        this.platFormImg = platFormImg;
    }

    public String getPlatformDesc() {
        return platformDesc;
    }

    public void setPlatformDesc(String platformDesc) {
        this.platformDesc = platformDesc;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

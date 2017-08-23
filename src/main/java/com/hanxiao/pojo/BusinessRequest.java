package com.hanxiao.pojo;

/**
 * Created by wenzhi on 17/6/23.
 */
public class BusinessRequest {
    private String type;
    private Integer begin;//从第几条开始查，默认是0
    private Integer offset;//每次查的数据量
    private String keyword;//关键字搜索

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

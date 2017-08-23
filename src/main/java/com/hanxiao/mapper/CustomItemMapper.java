package com.hanxiao.mapper;

import com.hanxiao.po.CustomItemData;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/7/15.
 */
public interface CustomItemMapper {
    List<CustomItemData> getCustomItemData(Map<String, Object> params) throws Exception;

    void insertCustomItemData(CustomItemData data) throws Exception;
}

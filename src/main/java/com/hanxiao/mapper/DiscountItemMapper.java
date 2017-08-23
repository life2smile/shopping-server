package com.hanxiao.mapper;

import com.hanxiao.po.DiscountItemData;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface DiscountItemMapper {
    List<DiscountItemData> findDiscountDataByType(String type) throws Exception;

    List<DiscountItemData> findDiscountDataWithOffset(Map<String, Object> map) throws Exception;

    void insertDiscountData(DiscountItemData data) throws Exception;

    void updateDiscountData(DiscountItemData data) throws Exception;

    void delDiscountData(long id) throws Exception;

}

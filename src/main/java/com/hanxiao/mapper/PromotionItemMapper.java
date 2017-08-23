package com.hanxiao.mapper;

import com.hanxiao.po.PromotionItemData;

import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
public interface PromotionItemMapper {
    List<PromotionItemData> findPromotionData() throws Exception;

    List<PromotionItemData> findPromotionDataWithOffset(Map<String, Object> map) throws Exception;

    void insertPromotionData(PromotionItemData data) throws Exception;

    void updatePromotionData(PromotionItemData data) throws Exception;

    void delPromotionData(long id) throws Exception;

}

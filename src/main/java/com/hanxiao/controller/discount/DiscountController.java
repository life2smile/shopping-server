package com.hanxiao.controller.discount;

import com.alibaba.fastjson.JSON;
import com.hanxiao.Test.CouponUtils;
import com.hanxiao.controller.common.BusinessUtil;
import com.hanxiao.mapper.DiscountItemMapper;
import com.hanxiao.mapper.TopBannerMapper;
import com.hanxiao.po.DiscountItemData;
import com.hanxiao.po.TopBannerData;
import com.hanxiao.pojo.BusinessRequest;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/22.
 */
@Controller
@RequestMapping("/v1")
public class DiscountController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "/getDiscountData", method = {RequestMethod.POST})
    @ResponseBody
    public Map getDiscountData(@RequestBody String request) {
        Map<String, Object> map = new HashMap<String, Object>();
        BusinessRequest businessRequest = JSON.parseObject(request, BusinessRequest.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", BusinessUtil.DISCOUNT_FIX_ITEM_NUM);
            params.put("type", businessRequest.getType());
            DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
            List<DiscountItemData> fixItems = itemMapper.findDiscountDataWithOffset(params);

            //查询剩下的item数据
            params.clear();
            params.put("begin", businessRequest.getBegin() + BusinessUtil.DISCOUNT_FIX_ITEM_NUM);
            params.put("offset", businessRequest.getOffset());
            params.put("type", businessRequest.getType());
            itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
            List<DiscountItemData> list = itemMapper.findDiscountDataWithOffset(params);

            list = initItemColor(list);

            //查询顶部的banner内容
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", BusinessUtil.DEFAULT_OFFSET);
            params.put("type", BusinessUtil.DISCOUNT_TYPE);
            TopBannerMapper topBannerMapper = sqlSession.getMapper(TopBannerMapper.class);
            List<TopBannerData> topBanners = topBannerMapper.getTopBanner(params);

            map.put("fixItems", fixItems);
            map.put("list", list);
            map.put("topBanner", topBanners);
            map.put("hasMore", list != null && list.size() == businessRequest.getOffset());
            map.put("helpLink", "http://119.23.35.74:8080/shopping/h5/qa.jsp");//这里写死helpLink
        } catch (Exception e) {
            map.clear();
            e.printStackTrace();
        }
        sqlSession.clearCache();//这里为了保证外部修改可以及时反馈到系统上，所以清空缓存。
        sqlSession.close();
        return map;
    }

    private List<DiscountItemData> initItemColor(List<DiscountItemData> list) {
        List<DiscountItemData> newList = new ArrayList<DiscountItemData>();
        if (list != null) {
            for (DiscountItemData item : list) {
                item.setPlatformBg(CouponUtils.getColor(item.getPlatformDesc()));
                newList.add(item);
            }
        }
        return newList;
    }
}

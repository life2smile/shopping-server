package com.hanxiao.controller.promotions;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.common.BusinessUtil;
import com.hanxiao.mapper.CustomItemMapper;
import com.hanxiao.mapper.PromotionItemBannerMapper;
import com.hanxiao.mapper.PromotionItemMapper;
import com.hanxiao.mapper.TopBannerMapper;
import com.hanxiao.po.CustomItemData;
import com.hanxiao.po.PromotionItemData;
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
@RequestMapping("/v1/getPromotionData.action")
public class PromotionController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(method = {RequestMethod.POST})
    @ResponseBody
    public Map getPromotionData(@RequestBody String request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BusinessRequest businessRequest = JSON.parseObject(request, BusinessRequest.class);
            HashMap<String, Object> params = new HashMap();
            params.put("begin", businessRequest.getBegin());
            params.put("offset", businessRequest.getOffset());
            //查列表数据
            PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
            List<PromotionItemData> items = itemMapper.findPromotionDataWithOffset(params);
            List<PromotionItemData> list = new ArrayList<PromotionItemData>();
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", 100);
            for (PromotionItemData itemData : items) {
                params.put("type", itemData.getType());
                PromotionItemBannerMapper bannerMapper = sqlSession.getMapper(PromotionItemBannerMapper.class);
                itemData.setBanners(bannerMapper.getBanners(params));
                list.add(itemData);
            }

            //查询顶部的banner内容
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", BusinessUtil.DEFAULT_OFFSET);
            params.put("type", BusinessUtil.PROMOTION_TYPE);
            params.put("subType", businessRequest.getType());
            TopBannerMapper topBannerMapper = sqlSession.getMapper(TopBannerMapper.class);
            List<TopBannerData> topBanners = topBannerMapper.getTopBanner(params);

            //查询顶部banner下面包含有两幅图片
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", 2);//总共返回2条数据即可
            CustomItemMapper customItemMapper = sqlSession.getMapper(CustomItemMapper.class);
            List<CustomItemData> twoItems = customItemMapper.getCustomItemData(params);

            //查询两幅图片下面的长图片画廊
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN + 2);//从第三条开始
            params.put("offset", 20);//总共返回20条数据即可
            List<CustomItemData> heightItems = customItemMapper.getCustomItemData(params);

            //查询查询第二个长图片画廊
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN + 22);//从第三条开始
            params.put("offset", 20);//总共返回20条数据即可
            List<CustomItemData> nextHeightItems = customItemMapper.getCustomItemData(params);


            map.put("list", list);
            map.put("topBanner", topBanners);//顶部的banner
            map.put("twoItems", twoItems);//banner下面的两条item数据
            map.put("heightItems", heightItems);
            map.put("nextHeightItems", nextHeightItems);
            map.put("hasMore", items != null && items.size() == businessRequest.getOffset());
        } catch (Exception e) {
            map.clear();
            e.printStackTrace();
        }
        sqlSession.clearCache();//这里为了保证外部修改可以及时反馈到系统上，所以清空缓存。
        sqlSession.close();
        return map;
    }
}

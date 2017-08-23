package com.hanxiao.controller.coupon;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.common.BusinessUtil;
import com.hanxiao.mapper.CouponItemMapper;
import com.hanxiao.mapper.TopBannerMapper;
import com.hanxiao.po.CouponItemData;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/6/21.
 */

@Controller
@RequestMapping("/v1")
public class CommodityController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "getCommodity", method = {RequestMethod.POST})
    @ResponseBody
    public Map getCouponCommodity(@RequestBody String request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BusinessRequest businessRequest = JSON.parseObject(request, BusinessRequest.class);
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("begin", businessRequest.getBegin());
            params.put("offset", businessRequest.getOffset());
            params.put("type", businessRequest.getType());
            //查询商品内容
            CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
            List<CouponItemData> list = itemMapper.findCouponDataWithOffset(params);

            //查询顶部的banner内容
            params.clear();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", BusinessUtil.DEFAULT_OFFSET);
            params.put("type", BusinessUtil.COUPON_TYPE);
            params.put("subType", businessRequest.getType());
            TopBannerMapper topBannerMapper = sqlSession.getMapper(TopBannerMapper.class);
            List<TopBannerData> topBanners = topBannerMapper.getTopBanner(params);

            map.put("list", list);
            map.put("topBanner", topBanners);//顶部的banner
            //当list的数目等于请求数时，说明还有数据，否则说明没有更多数据
            map.put("hasMore", list != null && list.size() == businessRequest.getOffset());
        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
        }
        sqlSession.clearCache();//这里为了保证外部修改可以及时反馈到系统上，所以清空缓存。
        sqlSession.close();
        return map;
    }

}

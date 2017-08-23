package com.hanxiao.controller.coupon;

import com.alibaba.fastjson.JSON;
import com.hanxiao.controller.common.BusinessUtil;
import com.hanxiao.mapper.TitleMapper;
import com.hanxiao.mapper.TopBannerMapper;
import com.hanxiao.po.CouponItemTitle;
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
@RequestMapping("/v1/titles.action")
public class TitleController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map getCouponTitles(@RequestBody String request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BusinessRequest businessRequest = JSON.parseObject(request, BusinessRequest.class);

            //获取领券区的titles
            TitleMapper titleMapper = sqlSession.getMapper(TitleMapper.class);
            List<CouponItemTitle> titles = titleMapper.getTitles(businessRequest.getType());

            //查询顶部的banner内容
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("begin", BusinessUtil.DEFAULT_BEGIN);
            params.put("offset", BusinessUtil.DEFAULT_OFFSET);
            params.put("type", BusinessUtil.COUPON_TYPE);
            TopBannerMapper topBannerMapper = sqlSession.getMapper(TopBannerMapper.class);
            List<TopBannerData> topBanners = topBannerMapper.getTopBanner(params);

            map.put("titles", titles);
            map.put("topBanners", topBanners);
            map.put("helpLink", "http://www.life2smile.com/shopping/h5/qa.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            map.clear();
        }
        sqlSession.clearCache();//这里为了保证外部修改可以及时反馈到系统上，所以清空缓存。
        sqlSession.close();
        return map;
    }
}

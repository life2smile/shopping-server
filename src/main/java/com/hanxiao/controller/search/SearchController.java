package com.hanxiao.controller.search;

import com.alibaba.fastjson.JSON;
import com.hanxiao.mapper.CouponItemMapper;
import com.hanxiao.po.CouponItemData;
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
public class SearchController {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @RequestMapping(value = "searchCommodity", method = {RequestMethod.POST})
    @ResponseBody
    public Map searchCommodity(@RequestBody String request) {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            BusinessRequest businessRequest = JSON.parseObject(request, BusinessRequest.class);
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("begin", businessRequest.getBegin());
            params.put("offset", businessRequest.getOffset());
            params.put("keyword", businessRequest.getKeyword());
            //查询商品内容
            CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
            List<CouponItemData> list = itemMapper.searchCouponDataWithOffset(params);

            map.put("list", list);
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

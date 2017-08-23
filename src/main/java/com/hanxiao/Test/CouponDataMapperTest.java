package com.hanxiao.Test;

import com.hanxiao.mapper.CouponItemMapper;
import com.hanxiao.mapper.TitleMapper;
import com.hanxiao.mapper.VersionMapper;
import com.hanxiao.po.CouponItemData;
import com.hanxiao.po.CouponItemTitle;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenzhi on 17/3/16.
 */
public class CouponDataMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws IOException {
        String resource = "myBatis/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂, 传入配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);

        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Test
    public void testFindCouponDataByType() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        List<CouponItemData> list = itemMapper.findCouponDataByType(null);
        System.out.println(list.get(0).getId());
    }

    @Test
    public void testFindCouponWithMap() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("begin", 0);
        map.put("offset", 1);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        List<CouponItemData> list = itemMapper.findCouponDataWithOffset(map);
        System.out.println(list.get(0).getId());
    }

    @Test
    public void testSearchCouponWithMap() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("begin", 0);
        map.put("offset", 10);
        map.put("keyword", "蘑菇");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        List<CouponItemData> list = itemMapper.searchCouponDataWithOffset(map);

    }

    @Test
    public void testInsertCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        itemMapper.insertCouponData(getData());
        sqlSession.commit();
        sqlSession.close();
    }

    private CouponItemData getData() {
        String type = "4";
        String description = "【24卡位】男女士防消磁银行卡套信用卡包名片夹卡片包\n";//推广文案
        double originPrice = 13.90;
        double couponPrice = 3.90;
        double couponValue = originPrice - couponPrice;
        String imgUrl = "http://s3.mogucdn.com/mlcdn/c45406/170728_504h6d3ifjg7382167ad7fcfe380f_640x960.jpg_468x468.jpg";
        String actionUrl = "https://g.mogujie.com/132JCBfU";//跳转链

        String platFormImg = "mogujie_icon.png";
        String platformDesc = "蘑菇街";

        CouponItemData data = new CouponItemData();
        data.setDescription(description);
        data.setCouponPrice(couponPrice);
        data.setCouponValue(couponValue);
        data.setOriginPrice(originPrice);
        data.setType(type);
        data.setImageUrl(imgUrl);
        data.setPlatformImg(platFormImg);
        data.setActionUrl(actionUrl);
        data.setPlatformDesc(platformDesc);
        return data;
    }

    @Test
    public void testUpdateCouponData() throws Exception {
        for (int i = 0; i < 40; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
            CouponItemData data = new CouponItemData();
            data.setDescription("商品测试");
            data.setCouponPrice(190 + i);
            data.setCouponValue(11 + i);
            data.setOriginPrice(189 + i);
            data.setId(i);
            data.setType(i % 6 + "");
            data.setDescription("蘑菇街专享");
            data.setActionUrl("https://g.mogujie.com/152Jhuzt");
            if (i % 2 == 0) {
                data.setImageUrl("https://gw.alicdn.com/bao/uploaded/i2/TB1.5jLOpXXXXaKapXXXXXXXXXX_!!0-item_pic.jpg_640x480Q50s50.jpg_.webp");
            } else {
                data.setImageUrl("http://img0.imgtn.bdimg.com/it/u=3395581086,3505521106&fm=21&gp=0.jpg");
            }
            itemMapper.updateCouponData(data);
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CouponItemMapper itemMapper = sqlSession.getMapper(CouponItemMapper.class);
        itemMapper.delCouponData(6);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testTitles() throws Exception {
//        List<CouponItemTitle> list;
//        BusinessRequest businessType = JSON.parseObject(null, BusinessRequest.class);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TitleMapper titleMapper = sqlSession.getMapper(TitleMapper.class);
        List<CouponItemTitle> list = titleMapper.getTitles(null);
    }


    @Test
    public void testVersion() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();

//        VersionMapper versionMapper = sqlSession.getMapper(VersionMapper.class);
//        VersionData version = versionMapper.getVersion();

//        System.out.println(checkNeedUpdate("0.2.5", "0.5.4"));
    }

    @Test
    public void insertDeviceId() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        VersionMapper versionMapper = sqlSession.getMapper(VersionMapper.class);
        versionMapper.insertUser("99909");
        sqlSession.commit();
    }


}

package com.hanxiao.Test;

import com.hanxiao.controller.common.BusinessUtil;
import com.hanxiao.mapper.CustomItemMapper;
import com.hanxiao.mapper.PromotionItemBannerMapper;
import com.hanxiao.mapper.PromotionItemMapper;
import com.hanxiao.po.CustomItemData;
import com.hanxiao.po.PromotionItemBanner;
import com.hanxiao.po.PromotionItemData;
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

/**
 * Created by wenzhi on 17/3/16.
 */
public class PromotionDataMapperTest {
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
        PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
        List<PromotionItemData> list = itemMapper.findPromotionData();
        System.out.println(list);
    }

    @Test
    public void testUpdateCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
        PromotionItemData data = new PromotionItemData();
        data.setDescription("test");
        data.setId(8);
        data.setType("3");
        itemMapper.updatePromotionData(data);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void queryItemData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        HashMap<String, Object> params = new HashMap();
        params.put("begin", BusinessUtil.DEFAULT_BEGIN);
        params.put("offset", BusinessUtil.DEFAULT_OFFSET);
        params.put("type", "1");
        PromotionItemBannerMapper bannerMapper = sqlSession.getMapper(PromotionItemBannerMapper.class);
        List list = bannerMapper.getBanners(params);
        System.out.println(list.size() + "---------------------");

    }

    @Test
    public void testDeleteCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
        itemMapper.delPromotionData(8);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testQueryItems() throws Exception {
        HashMap<String, Object> params = new HashMap();
        params.put("begin", BusinessUtil.DEFAULT_BEGIN);
        params.put("offset", 5);//总共返回5条数据即可
        CustomItemMapper customItemMapper = sqlSessionFactory.openSession().getMapper(CustomItemMapper.class);
        List<CustomItemData> customItems = customItemMapper.getCustomItemData(params);
    }

    /**
     * 插入item数据
     *
     * @throws Exception
     */
    @Test
    public void testInsertCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PromotionItemMapper itemMapper = sqlSession.getMapper(PromotionItemMapper.class);
        itemMapper.insertPromotionData(getData());
        sqlSession.commit();
        sqlSession.close();
    }

    private PromotionItemData getData() {
        String desc = "精品女鞋";
        String subDesc = "送给美美哒，靓丽的自己";
        String type = "6";//type可以标识类目,指示下面的小资源
        String actionUrl = "https://g.mogujie.com/1c2JYg9i";
        String imgUrl = "http://s17.mogucdn.com/mlcdn/c45406/170820_83glc5jjg2d4jeelie133009lk4j2_640x960.jpg_468x468.jpg";
        double originPrice = 26.00;
        double promotionPrice = 19.9;

        PromotionItemData data = new PromotionItemData();
        data.setDescription(desc);
        data.setType(type);
        data.setActionUrl(actionUrl);
        data.setImgUrl(imgUrl);
        data.setSubDescription(subDesc);
        data.setOriginPrice(originPrice);
        data.setPromotionPrice(promotionPrice);
        return data;
    }


    /**
     * 插入item下面的小图资源数据
     *
     * @throws Exception
     */
    @Test
    public void testInsertItemData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PromotionItemBannerMapper itemMapper = sqlSession.getMapper(PromotionItemBannerMapper.class);
        itemMapper.insertBanners(getItemBannerData());
        sqlSession.commit();
        sqlSession.close();
    }

    //type:0:男装 1:女装2:吃货3:运动4:美妆5：男鞋子6：女鞋子
    private PromotionItemBanner getItemBannerData() {
        PromotionItemBanner data = new PromotionItemBanner();
        String type = "6";
        double price = 55.00;
        String actionUrl = "https://g.mogujie.com/1d2JElo7";
        String imgUrl = "http://s3.mogucdn.com/p2/161231/144185663_5bc50eec9cbba7jg1b7aa04e4ebdd_640x960.jpg_468x468.jpg";

        data.setType(type);
        data.setPrice(price);
        data.setActionUrl(actionUrl);
        data.setImgUrl(imgUrl);

        return data;
    }


    //banner下面的数据包括横着的和竖着的
    @Test
    public void testInsertCumtomItem() throws Exception {
        CustomItemData data = new CustomItemData();
        String actionUrl = "https://g.mogujie.com/102JEeR1";
        String imgUrl = "http://s3.mogucdn.com/p1/160228/1hjig3_ifrgcmjsmy3dizdggyzdambqmeyde_640x960.jpg_468x468.jpg";
        String desc = "@新款韩版修身体恤衫纯棉圆领男士短袖T恤潮男夏季打底衫半袖\n";
        double originPrice = 42.86;
        double promotionPrice = 30;

        data.setImgUrl(imgUrl);
        data.setDescription(desc);
        data.setActionUrl(actionUrl);
        data.setOriginPrice(originPrice);
        data.setPromotionPrice(promotionPrice);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        CustomItemMapper customItemMapper = sqlSession.getMapper(CustomItemMapper.class);
        customItemMapper.insertCustomItemData(data);
        sqlSession.commit();
        sqlSession.close();
    }
}

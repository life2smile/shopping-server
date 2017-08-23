package com.hanxiao.Test;

import com.hanxiao.mapper.DiscountItemMapper;
import com.hanxiao.po.DiscountItemData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public class DiscountDataMapperTest {
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
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        List<DiscountItemData> list = itemMapper.findDiscountDataByType("1");
        System.out.println(list);
    }

    @Test
    public void testUpdateCouponData() throws Exception {
        for (int i = 80; i < 120; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
            DiscountItemData data = new DiscountItemData();
            data.setDescription("很多人都来买了，谁不买，快点！" + i);
            data.setType(i % 6 + "");
            data.setTitle("绝对大甩卖!" + i);
            data.setOriginPrice(i + 10);
            data.setCouponPrice(i + 1);
            data.setDiscount((i + 10) / (i + 1));
            data.setId(i);
            if (i % 2 == 0) {
                data.setImageUrl("https://gw.alicdn.com/bao/uploaded/i2/TB1.5jLOpXXXXaKapXXXXXXXXXX_!!0-item_pic.jpg_640x480Q50s50.jpg_.webp");
            } else {
                data.setImageUrl("http://img0.imgtn.bdimg.com/it/u=3395581086,3505521106&fm=21&gp=0.jpg");
            }
            itemMapper.updateDiscountData(data);
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        itemMapper.delDiscountData(7);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testInsertCouponData() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DiscountItemMapper itemMapper = sqlSession.getMapper(DiscountItemMapper.class);
        itemMapper.insertDiscountData(getData());
        sqlSession.commit();
        sqlSession.close();
    }

    public DiscountItemData getData() {
        String title = "范冰冰潮流墨镜女百搭韩版时尚新款大框圆脸瘦脸反光太阳镜女";
        String desc = "眼镜见过不少 但见到它内心还是澎湃了 不挑脸型， 从去年一直火到现在 适合各种脸型，很多网红也在带 圆形的设计 金属质感棒棒哒 自留款 出游 聚会 外出 上班路上 我觉得都是挺适合带 而且墨镜也不挑季节 还能外出能做很好的修饰 自留款 哈哈";
        String type = "";
        String imgUrl = "http://s3.mogucdn.com/p2/161217/10715149_4d716kh1ag04bfjk1fbibhj61e084_640x960.jpg_468x468.jpg";
        String actionUrl = "https://g.mogujie.com/162JEaRP";
        String platformDesc = "蘑菇街专享";
        double originPrice = 18.80;
        double promotionPrice = 0;
        double discount = 0;

        DiscountItemData data = new DiscountItemData();
        data.setDescription(desc);
        data.setTitle(title);
        data.setType(type);
        data.setImageUrl(imgUrl);
        data.setActionUrl(actionUrl);
        data.setPlatformDesc(platformDesc);
        data.setOriginPrice(originPrice);
        data.setCouponPrice(promotionPrice);
        data.setDiscount(discount);
        return data;
    }

}

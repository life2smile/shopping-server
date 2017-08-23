package com.hanxiao.Test.demotest;

import com.hanxiao.mapper.testmapper.OrdersMapperCustom;
import com.hanxiao.mapper.testmapper.UserMapper;
import com.hanxiao.po.testpo.User;
import com.hanxiao.po.testpo.UserCustom;
import com.hanxiao.po.testpo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public class UserMapperTest {
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
    public void testFindUserById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(2);
        System.out.println(user);
    }

    @Test
    public void testFindUserByIdResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserByIdResultMap(2);
        System.out.println(user);
    }


    @Test
    public void testFindUserByName() throws Exception {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> list = userMapper.findUserByName("%三%");
//        System.out.println(list);
    }

    @Test
    public void testFindUserList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo vo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("三");
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(3);
//        ids.add(4);
        vo.setIds(ids);
        vo.setUserCustom(userCustom);
        List<UserCustom> list = userMapper.findUserList(vo);
        System.out.println(list);
    }

    @Test
    public void testFindUserCount() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserQueryVo vo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("三");
        vo.setUserCustom(userCustom);
        int count = userMapper.findUserCount(vo);
        System.out.println(count);
    }

    @Test
    public void testOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);
        List customs = ordersMapperCustom.findOrdersUser();
        System.out.println(customs);
        sqlSession.close();
    }

    @Test
    public void someTest(){

        double pi = 3.1415926;
        double h = 70;
        double r = 55;

        double R = 0;

        R = (r*r + 4900)/140;

        double v = pi*h*h*(R - h/3);
        System.out.println(v+"---------------"+R);

    }


}

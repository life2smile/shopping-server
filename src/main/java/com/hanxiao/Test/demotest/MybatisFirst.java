package com.hanxiao.Test.demotest;

import com.hanxiao.po.testpo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by wenzhi on 17/3/14.
 */
public class MybatisFirst {

    private SqlSession sqlSession;

    @Before
    public void setUp() throws IOException {
        String resource = "myBatis/sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂, 传入配置信息
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        //通过工厂得到session
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void findUserById() throws IOException {
        //通过session操作数据库
        //第一个参数
        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void findUserByName() {
        List<User> list = sqlSession.selectList("test.findUserByName", "%三%");
        System.out.println(list);
        sqlSession.close();
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setAddress("成都");
        user.setBirthday(new Date());
        user.setUsername("张三胖");
        user.setSex("1");
        sqlSession.insert("test.insertUser", user);
        System.out.println(user.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(12);
        user.setAddress("柘城");
        user.setBirthday(new Date());
        user.setUsername("张三胖");
        user.setSex("1");
        sqlSession.update("test.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void delUser(){
        sqlSession.delete("test.delUserById", 11);
        sqlSession.commit();
        sqlSession.close();
    }
}

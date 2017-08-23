package com.hanxiao.mapper.testmapper;

import com.hanxiao.po.testpo.User;
import com.hanxiao.po.testpo.UserCustom;
import com.hanxiao.po.testpo.UserQueryVo;

import java.util.List;

/**
 * Created by wenzhi on 17/3/16.
 */
public interface UserMapper {
    User findUserById(int id) throws Exception;

//    List<User> findUserByName(String name) throws Exception;


    void insertUser(User user) throws Exception;

    void delUserById(int id) throws Exception;

    List<UserCustom> findUserList(UserQueryVo vo) throws Exception;

    int findUserCount(UserQueryVo vo) throws Exception;


    User findUserByIdResultMap(int id) throws Exception;
}

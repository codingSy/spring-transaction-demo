package com.missfresh.spring.transaction.demo.dao;

import com.missfresh.spring.transaction.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int deleteAll();

    int count();

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
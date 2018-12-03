package com.missfresh.spring.transaction.demo.service;

import com.missfresh.spring.transaction.demo.dao.UserMapper;
import com.missfresh.spring.transaction.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 没有实现类的话事务实现方式是cglib
 *
 * @author shaoying@missfresh.cn
 */
@Slf4j
@Component("otherService")
public class OtherService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void parent() {
        try {
            child();
        } catch (Exception e) {
            log.error("child方法发生异常！", e);
        }
        User user = new User("parent");
        userMapper.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void child() {
        User user = new User("child");
        userMapper.insert(user);
        int a = 1 / 0;
    }
}
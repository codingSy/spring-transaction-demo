package com.missfresh.spring.transaction.demo.service.impl;


import com.missfresh.spring.transaction.demo.dao.UserMapper;
import com.missfresh.spring.transaction.demo.entity.User;
import com.missfresh.spring.transaction.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 父事务
     * 需求：
     * 1.parent必须要调用child
     * 2.child方法不是那么重要，也就是child成功与否对parent的执行没有影响
     *
     * 预期效果：
     * 1.child回滚
     * 2.parent插入成功
     *
     * 真实结果：child、parent均插入成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void parent() {
        try {
            //内部调用会使事务失效
//            child();
//            UserService userServiceProxy = (UserService) AopContext.currentProxy();
//            userServiceProxy.child();
            userService.child();
        } catch (Exception e) {
            log.error("child方法发生异常！");
        }
        User user = new User("parent");
        userMapper.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void child() {
        User user = new User("child");
        userMapper.insert(user);
        int a = 1/0;
    }

}
package com.missfresh.spring.transaction.demo;

import com.missfresh.spring.transaction.demo.dao.UserMapper;
import com.missfresh.spring.transaction.demo.entity.User;
import com.missfresh.spring.transaction.demo.service.UserService;
import com.missfresh.spring.transaction.demo.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootTest()
@Slf4j
public class SpringTransactionDemoApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private OtherService otherService;
    @Autowired
    private UserMapper userMapper;

    @Before
    public void init() {
        log.info("==============================清空所有数据,初始化中...==============================");
        userMapper.deleteAll();
    }

    @Test
    public void testParent() throws IOException {
        log.info("parent start.....");
        try {
            userService.parent();
        } catch (Exception e) {
            log.error("parent发生异常:{}", e.getMessage());
        }
        log.info("parent end.....");
//        System.in.read();
    }

    @Test
    public void testChild() {
        try {
            userService.child();
        } catch (Exception e) {
            log.error("child发生异常:{}", e.getMessage(), e);
        }
    }

    @Test
    public void testOther() {
        try {
            otherService.parent();
        } catch (Exception e) {
            log.error("child发生异常:{}", e.getMessage(), e);
        }
    }

    @Test
    public void getClassProxyType() {
        System.out.println("userService isCglibProxy----"+ AopUtils.isCglibProxy(userService));
        System.out.println("userService isJdkDynamicProxy----"+ AopUtils.isJdkDynamicProxy(userService));

        System.out.println("otherService isCglibProxy----"+ AopUtils.isCglibProxy(otherService));
        System.out.println("otherService isJdkDynamicProxy----"+ AopUtils.isJdkDynamicProxy(otherService));
    }

    @After
    public void getName() {
        List<User> users = userMapper.selectAll();
        if (!CollectionUtils.isEmpty(users)) {
            users.stream().forEach(user -> System.out.println("新增name:-------" + user.getName()));
            log.info("=========================结束测试,新增记录:{}=========================", users.size());
        } else {
            log.info("==============================结束测试(无数据)==============================");
        }
    }

}

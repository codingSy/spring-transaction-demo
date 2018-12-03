/*
 * Copyright (C) 2018 www.missfresh.cn All rights reserved.
 *
 * Created by shaoying@missfresh.cn on 2018/11/18.
 */

package com.missfresh.spring.transaction.demo.proxy.impl;

import com.missfresh.spring.transaction.demo.proxy.Transaction;
import com.missfresh.spring.transaction.demo.proxy.MyService;
import lombok.extern.slf4j.Slf4j;

/**
 * 描述类的功能.
 *
 * @author shaoying@missfresh.com
 */
@Slf4j
public class MyServiceImpl implements MyService {

    @Transaction
    @Override
    public void test() {
        System.out.println("------------------MyServiceImpl test doing-----------------");
        this.test1();
    }

    @Transaction
    @Override
    public void test1() {
        System.out.println("------------------MyServiceImpl test1 doing-----------------");

    }

    @Override
    public void abc() {
        System.out.println("------------------MyServiceImpl abc-----------------");

    }
}

/*
 * Copyright (C) 2018 www.missfresh.cn All rights reserved.
 *
 * Created by shaoying@missfresh.cn on 2018/11/18.
 */

package com.missfresh.spring.transaction.demo.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述类的功能.
 *
 * @author shaoying@missfresh.com
 */
@Slf4j
public class OrderService {

    @Transaction
    public void test() {
        System.out.println("------------------OrderServiceImpl test doing-----------------");
        this.test1();
    }

    @Transaction
    public void test1() {
        System.out.println("------------------OrderServiceImpl test1 doing-----------------");

    }

    public void abc() {
        System.out.println("------------------OrderServiceImpl abc-----------------");

    }
}

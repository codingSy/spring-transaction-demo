/*
 * Copyright (C) 2018 www.missfresh.cn All rights reserved.
 *
 * Created by shaoying@missfresh.cn on 2018/11/18.
 */

package com.missfresh.spring.transaction.demo.proxy;

import com.missfresh.spring.transaction.demo.proxy.impl.MyServiceImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 描述类的功能.
 *
 * @author shaoying@missfresh.com
 */
public class JdkProxy implements InvocationHandler {

    /**
     * 目标对象
     */
    public Object target;

    public JdkProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result;
        Annotation annotation = target.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes()).
                getAnnotation(Transaction.class);
        if (annotation == null) {
            System.out.println("方法" + method.getName() + ",无事务");
            result = method.invoke(target, args);
        } else {
            System.out.println("方法" + method.getName() + ",开始事务");
            result = method.invoke(target, args);
            System.out.println("方法" + method.getName() + ",结束事务");
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        JdkProxy handler = new JdkProxy(new MyServiceImpl());
        MyService proxy = (MyService) Proxy.newProxyInstance(JdkProxy.class.getClassLoader(),
                new Class[]{MyService.class}, handler);
        proxy.test();
//        proxy.test1();
//        proxy.abc();
//        System.in.read();
    }
}

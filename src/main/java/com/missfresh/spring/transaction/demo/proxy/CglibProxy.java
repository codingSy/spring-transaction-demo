package com.missfresh.spring.transaction.demo.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result;
        Annotation annotation = obj.getClass()
                .getSuperclass()
                .getDeclaredMethod(method.getName(), method.getParameterTypes())
                .getAnnotation(Transaction.class);
        if (annotation == null) {
            System.out.println("方法" + method.getName() + "无事务");
            result = proxy.invoke(target, args);
        } else {
            System.out.println("方法" + method.getName() + "事务开始");
            result = proxy.invokeSuper(obj, args);
            System.out.println("方法" + method.getName() + "事务结束");
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        //生成$Proxy0的class文件
        CglibProxy cglib = new CglibProxy();
        OrderService orderCglib =(OrderService) cglib.getInstance(new OrderService());
        orderCglib.test();
//        bookCglib.abc();
        System.in.read();
    }
}

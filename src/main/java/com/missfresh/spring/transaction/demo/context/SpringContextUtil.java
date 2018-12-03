package com.missfresh.spring.transaction.demo.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
    public class SpringContextUtil implements ApplicationContextAware {
        private static ApplicationContext context;
     
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            context = applicationContext;
        }
        
        public static ApplicationContext getApplicationContext() {
            return context;
        }
    }
package com.pdxx.ms.April.April_4_10;

import java.lang.reflect.Method;

/**
 * 反射方式实现注解处理器
 */

public class TestAnnotation1Tracker {
    public static void trackerReflectAnnotation(Class cl) {
        //getDeclaredMethod 或许所有声明的方法
        for (Method method : cl.getDeclaredMethods()) {
            //或许所有含有test注解的方法
            TestAnnotation1 annotation = method.getAnnotation(TestAnnotation1.class);
            if (annotation != null) {
                System.out.print(annotation.test() + annotation.testBoolean() + annotation.testInt());
            }
        }
    }

}

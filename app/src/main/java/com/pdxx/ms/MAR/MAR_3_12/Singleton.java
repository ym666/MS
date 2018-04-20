package com.pdxx.ms.MAR.MAR_3_12;

/**
 *
 * 设计模式之单例模式写法之三 饿汉式
 * 这种方式在类加载时就完成了初始化。所以类加载比较慢但获取对象的速度很快。基于类加载所以避免了同步的问题
 */

public class Singleton {
    private static Singleton singleton = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return singleton;
    }

}

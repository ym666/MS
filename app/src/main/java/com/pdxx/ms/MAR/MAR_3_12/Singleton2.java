package com.pdxx.ms.MAR.MAR_3_12;

/**
 * 设计模式之单例模式写法之二 懒汉式（线程不安全）
 * 懒汉式在类中首先声明一个静态对象，在第一次调用时初始化对象。
 * 虽然节约了资源，但是第一次初始化时比较慢，而且线程不安全，在多线程中不能操作。
 */

public class Singleton2 {
    private static Singleton2 singleton2;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            return new Singleton2();
        }
        return singleton2;
    }

}

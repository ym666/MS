package com.pdxx.ms.MAR.MAR_3_13;

/**
 * 懒汉式 线程不安全
 */

public class Singleton2 {
    private static Singleton2 singleton2;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}

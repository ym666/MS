package com.pdxx.ms.MAR.MAR_3_12;

/**
 * 设计模式之单例模式写法之五 双重加锁模式
 * 第一次判空是为了不必要的同步 第二次判空是在实例为空时才建立新实例
 */

public class Singleton5 {
    private static volatile Singleton5 singleton5;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if (singleton5 == null) {
            synchronized (Singleton5.class) {
                if (singleton5 == null) {
                    return new Singleton5();
                }
            }
        }
        return singleton5;
    }
}

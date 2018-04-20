package com.pdxx.ms.MAR.MAR_3_12;

/**
 * 设计模式之单例模式写法之四 懒汉式（线程安全）
 * <p>
 * 这种写法虽然能在多线程中使用，但每一次初始化时都要进行一个同步，造成不必要的同步浪费
 */

public class Singleton4 {
    private static Singleton4 singleton4;

    private Singleton4() {
    }

    public static synchronized Singleton4 getInstance() {
        if (singleton4 == null) {
            return new Singleton4();
        }
        return singleton4;
    }
}

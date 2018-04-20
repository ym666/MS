package com.pdxx.ms.MAR.MAR_3_12;

/**
 * 设计模式之单例模式写法之三 静态内部类形式
 * (推荐写法)
 */

public class Singleton3 {

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return Singleton3Holer.singleton3;
    }

    private static class Singleton3Holer {
        private static final Singleton3 singleton3 = new Singleton3();
    }
}

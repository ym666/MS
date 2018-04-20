package com.pdxx.ms.MAR.MAR_3_13;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Singleton5 {
    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        return Singleton5Holder.singleton5;
    }

    private static class Singleton5Holder {
        private static final Singleton5 singleton5 = new Singleton5();
    }

}

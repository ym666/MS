package com.pdxx.ms.MAR.MAR_3_13;

/**
 * Created by Administrator on 2018/3/13.
 */

public class Singleton3 {
    private static Singleton3 singleton3;

    private Singleton3() {
    }

    public synchronized static Singleton3 getInstance() {
        if (singleton3 == null) {
            singleton3 = new Singleton3();
        }
        return singleton3;
    }
}

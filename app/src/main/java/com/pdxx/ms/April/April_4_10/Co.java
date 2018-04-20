package com.pdxx.ms.April.April_4_10;

/**
 * Created by Administrator on 2018/4/10.
 */

public class Co<T, K> {
    T testt;
    K testk;

    public Co(T testt, K testk) {
        this.testt = testt;
        this.testk = testk;
    }

    @Override
    public String toString() {
        return "Co{" +
                "testt=" + testt +
                ", testk=" + testk +
                '}';
    }
}

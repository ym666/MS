package com.pdxx.ms.April.April_4_10;

/**
 * Created by Administrator on 2018/4/10.
 */

public class TestClient {
    public static void main(String[] args) {
        TestAnnotation1Tracker.trackerReflectAnnotation(TestClient.class);
    }

    @TestAnnotation1()
    public void test1() {

    }
}

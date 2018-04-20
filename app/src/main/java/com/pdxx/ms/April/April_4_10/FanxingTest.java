package com.pdxx.ms.April.April_4_10;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class FanxingTest {
    public static void test1() {
        List<String> strs = new ArrayList<>();
//        test2(strs);
    }

    public static void test2(List<Object> objects) {
        for (Object object : objects) {
            object.toString();
        }
        objects.add(1);
    }

    public static void test3() {
        Co<Integer, String> co = new Co<>(1, "2");
        SysUtil.sys(co.toString());
    }

    public static void main(String[] args) {
        test4();
    }

    public static void main(String arg) {
        test4();
    }

    public static void test4() {
        FanxingInterface<Integer> fanxingInterface = new FanxingInterface<Integer>() {
            @Override
            public Integer get() {
                return 4;
            }
        };
    }
}

package com.pdxx.ms.April.April_4_18;

import java.util.Random;

/**
 * Created by Administrator on 2018/4/18.
 */

public class BaseModel {
    //处理数据，请求等操作
    public int toLogin() {
        Random random = new Random();
        return random.nextInt(100);
    }
}

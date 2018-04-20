package com.pdxx.ms.April.April_4_18;

/**
 * Created by Administrator on 2018/4/18.
 */

public class BasePresenter<T extends BaseView> {
    //view 跟model的桥梁
    BaseModel model = new BaseModel();
    T view;

    public BasePresenter(T view) {
        this.view = view;
    }

}

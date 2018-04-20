package com.pdxx.ms.April.April_4_18;

/**
 * Created by Administrator on 2018/4/18.
 */

public class MvpTestPresenter extends BasePresenter {


    public MvpTestPresenter(BaseView view) {
        super(view);
    }

    void login() {
        int i = model.toLogin();
        if (i < 50) {
            view.loginSuccess();
            ((MvpTestView) view).getRandom(i);
        } else {
            view.loginFail();
            ((MvpTestView) view).getRandom(i);
        }
    }
}

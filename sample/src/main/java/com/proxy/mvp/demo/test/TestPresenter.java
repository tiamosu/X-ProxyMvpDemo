package com.proxy.mvp.demo.test;

import android.util.Log;

/**
 * @author xia
 * @date 2018/7/19.
 */
public class TestPresenter extends BasePresenter<TestView> {

    public void load() {
        Log.e("weixi", "load");
        final String userId = "10086";
        getView().showUserId(userId);

        final boolean isChecked = getView().isChecked();
        Log.e("weixi", "boolean:" + isChecked);
    }
}

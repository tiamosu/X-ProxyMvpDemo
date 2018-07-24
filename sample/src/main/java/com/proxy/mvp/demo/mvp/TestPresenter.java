package com.proxy.mvp.demo.mvp;

import android.util.Log;

/**
 * @author xia
 * @date 2018/7/19.
 */
public class TestPresenter extends BasePresenter<TestView> {

    public void load() {
        Log.e("weixi", "load");

        final String userId = "10086";
        getV().showUserId(userId);
        Log.e("weixi", "getView:" + getV());

        final boolean isChecked = getV().isChecked();
        Log.e("weixi", "boolean:" + isChecked);
    }
}

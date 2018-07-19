package com.proxy.mvp.demo.example;

import android.util.Log;

import com.proxy.mvp.demo.mvp.MvpPresent;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings("WeakerAccess")
public class ExamplePresent extends MvpPresent<IExampleView> {

    public void load() {
        Log.e("weixi", "load");
        final String userId = "10086";
        getV().showUserId(userId);
    }
}

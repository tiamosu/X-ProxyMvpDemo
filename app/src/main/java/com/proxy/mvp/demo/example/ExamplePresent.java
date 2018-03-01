package com.proxy.mvp.demo.example;

import com.proxy.mvp.demo.mvp.MvpPresent;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings("WeakerAccess")
public class ExamplePresent extends MvpPresent<IExampleView> {

    public void load() {
        final String userId = "10086";
        getV().showUserId(userId);
    }
}

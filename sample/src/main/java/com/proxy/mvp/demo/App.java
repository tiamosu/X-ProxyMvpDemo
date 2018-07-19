package com.proxy.mvp.demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * @author xia
 * @date 2018/3/1.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}

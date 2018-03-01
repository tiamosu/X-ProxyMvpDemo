package com.proxy.mvp.demo.mvp;

import android.content.Context;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings("WeakerAccess")
public interface IView<P> {

    P newP();

    int getLayoutId();

    Context getContext();

    void initData();

    void initView();

    void initEvent();

    void loadData();
}

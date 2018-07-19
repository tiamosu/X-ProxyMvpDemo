package com.proxy.mvp.demo.test;

import com.xia.mvp.MvpNullObjectBasePresenter;
import com.xia.mvp.MvpPresenter;

/**
 * @author xia
 * @date 2018/7/19.
 */
public class BasePresenter<V extends BaseMvpView> extends MvpNullObjectBasePresenter<V> implements MvpPresenter<V> {
}

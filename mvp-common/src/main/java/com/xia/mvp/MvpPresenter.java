package com.xia.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

/**
 * @author xia
 * @date 2018/7/19.
 */
public interface MvpPresenter<V extends MvpView> {

    @UiThread
    void attachView(@NonNull V view);

    @UiThread
    void detachView();

    @UiThread
    void destroy();
}

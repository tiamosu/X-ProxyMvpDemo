package com.proxy.mvp.demo.mvp;

/**
 * @author xia
 * @date 2018/3/1.
 */

public class MvpPresent<V extends IView> implements IPresent<V> {
    private V mProxyView;

    @Override
    public void attachV(V view) {
        this.mProxyView = view;
    }

    protected V getV() {
        return mProxyView;
    }
}

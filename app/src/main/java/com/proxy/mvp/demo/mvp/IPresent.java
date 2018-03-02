package com.proxy.mvp.demo.mvp;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings("WeakerAccess")
public interface IPresent<V extends IView> {

    void attachV(V view);
}
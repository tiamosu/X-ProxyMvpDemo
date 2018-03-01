package com.proxy.mvp.demo.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class MvpPresent<V extends IView> implements IPresent<V> {
    private WeakReference<V> mWeakView;
    private V mProxyView;

    @Override
    public void attachV(V view) {
        mWeakView = new WeakReference<>(view);
        final MvpViewInvocationHandler invocationHandler = new MvpViewInvocationHandler(mWeakView.get());
        mProxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(), view.getClass()
                        .getInterfaces(), invocationHandler);
        Proxy.getProxyClass(view.getClass().getClassLoader(), view.getClass().getInterfaces());
    }

    @Override
    public void detachV() {
        if (mWeakView.get() != null) {
            mWeakView.clear();
        }
        mWeakView = null;
    }

    protected V getV() {
        return mProxyView;
    }

    protected boolean isViewDetach() {
        return mWeakView == null || mWeakView.get() == null;
    }

    private class MvpViewInvocationHandler implements InvocationHandler {
        private IView mvpView;

        public MvpViewInvocationHandler(IView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!isViewDetach()) {
                return method.invoke(mvpView, args);
            }
            return null;
        }
    }
}

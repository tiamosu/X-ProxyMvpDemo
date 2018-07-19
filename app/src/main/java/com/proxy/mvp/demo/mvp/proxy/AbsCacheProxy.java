package com.proxy.mvp.demo.mvp.proxy;

import com.proxy.mvp.demo.mvp.IView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xia
 * @date 2018/3/2.
 */
public class AbsCacheProxy implements InvocationHandler {
    private WeakReference<IView> mView;

    public static AbsCacheProxy create() {
        return new AbsCacheProxy();
    }

    @SuppressWarnings("unchecked")
    protected <V extends IView> V proxy(V view) {
        return (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), this);
    }

    protected void bind(IView view) {
        if (view != null) {
            mView = new WeakReference<>(view);
        }
    }

    protected void unBind() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (mView != null && mView.get() != null) {
            return invokeMethod(mView.get(), method, args);
        }
        return null;
    }

    private Object invokeMethod(Object handlerView, Method method, Object[] args) {
        if (handlerView == null || method == null) {
            return null;
        }
        try {
            return method.invoke(handlerView, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

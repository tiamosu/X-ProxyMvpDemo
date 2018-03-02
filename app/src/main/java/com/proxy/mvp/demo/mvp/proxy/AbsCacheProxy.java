package com.proxy.mvp.demo.mvp.proxy;

import android.support.v4.util.ArrayMap;

import com.proxy.mvp.demo.mvp.IView;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author xia
 * @date 2018/3/2.
 */

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class AbsCacheProxy implements InvocationHandler {
    private final Map<Method, Object[]> mViewCaches = new ArrayMap<>();
    private WeakReference<IView> mView;

    public static AbsCacheProxy create() {
        return new AbsCacheProxy();
    }

    protected <V extends IView> V proxy(V view) {
        return (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), this);
    }

    protected void bind(IView view) {
        if (view != null) {
            mView = new WeakReference<>(view);
            for (Method method : mViewCaches.keySet()) {
                invokeMethod(view, method, mViewCaches.get(method));
            }
        }
    }

    protected void unBind() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
        mViewCaches.clear();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (isCacheMethod(method)) {
            cacheMethod(method, args);
        }
        if (mView != null && mView.get() != null) {
            return invokeMethod(mView.get(), method, args);
        }
        return null;
    }

    protected boolean isCacheMethod(Method method) {
        final CacheMethod cacheMethod = method.getAnnotation(CacheMethod.class);
        return cacheMethod != null && cacheMethod.isCached();
    }

    protected void cacheMethod(Method method, Object[] args) {
        mViewCaches.put(method, args);
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    protected Object invokeMethod(Object handlerView, Method method, Object[] args) {
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

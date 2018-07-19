package com.proxy.mvp.demo.mvp.proxy;

import com.proxy.mvp.demo.mvp.IView;

/**
 * @author xia
 * @date 2018/3/2.
 */
public final class MvpProxyHelper {

    public static AbsCacheProxy create() {
        return AbsCacheProxy.create();
    }

    public static <V extends IView> V proxy(AbsCacheProxy proxy, V view) {
        return proxy != null ? proxy.proxy(view) : null;
    }

    public static <V extends IView> void bind(AbsCacheProxy proxy, V view) {
        if (proxy != null) {
            proxy.bind(view);
        }
    }

    public static void unBind(AbsCacheProxy proxy) {
        if (proxy != null) {
            proxy.unBind();
        }
    }
}

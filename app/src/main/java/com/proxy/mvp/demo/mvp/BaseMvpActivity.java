package com.proxy.mvp.demo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.proxy.mvp.demo.mvp.proxy.AbsCacheProxy;
import com.proxy.mvp.demo.mvp.proxy.MvpProxyHelper;

/**
 * @author xia
 * @date 2018/3/1.
 */

public abstract class BaseMvpActivity<P extends IPresent> extends AppCompatActivity implements IView<P> {
    private P mPresent;
    private AbsCacheProxy mMvpCacheProxy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();

        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            initData();
            initView();
            initEvent();
            loadData();
        }
    }

    @Override
    public BaseMvpActivity getContext() {
        return this;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
    }

    private void initMvp() {
        mMvpCacheProxy = onCreateCacheProxy();
        MvpProxyHelper.bind(mMvpCacheProxy, this);
    }

    @SuppressWarnings("unchecked")
    private <PROXY extends AbsCacheProxy> PROXY onCreateCacheProxy() {
        return (PROXY) MvpProxyHelper.create();
    }

    protected final <V extends IView> V defaultProxy(V view) {
        if (mMvpCacheProxy == null) {
            mMvpCacheProxy = onCreateCacheProxy();
        }
        return MvpProxyHelper.proxy(mMvpCacheProxy, view);
    }

    @SuppressWarnings("unchecked")
    protected P getP() {
        if (mPresent == null) {
            mPresent = newP();
            if (mPresent != null) {
                mPresent.attachV(defaultProxy(this));
            }
        }
        return mPresent;
    }

    @Override
    protected void onDestroy() {
        MvpProxyHelper.unBind(mMvpCacheProxy);
        mPresent = null;
        mMvpCacheProxy = null;
        super.onDestroy();
    }
}

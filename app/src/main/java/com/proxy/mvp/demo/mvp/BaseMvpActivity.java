package com.proxy.mvp.demo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author xia
 * @date 2018/3/1.
 */

public abstract class BaseMvpActivity<P extends IPresent> extends AppCompatActivity implements IView<P> {
    private P mP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @SuppressWarnings("unchecked")
    protected P getP() {
        if (mP == null) {
            mP = newP();
            if (mP != null) {
                mP.attachV(this);
            }
        }
        return mP;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getP() != null) {
            getP().detachV();
        }
        mP = null;
    }
}

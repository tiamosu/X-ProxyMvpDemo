package com.proxy.mvp.demo.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xia.mvp.MvpPresenter;
import com.xia.mvp.MvpView;

/**
 * @author xia
 * @date 2018/7/19.
 */
public abstract class BaseMvpActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView<P> {
    private P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();
    }

    @SuppressWarnings("unchecked")
    private void initMvp() {
        if (mPresenter == null) {
            mPresenter = newP();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected P getP() {
        return mPresenter == null ? newP() : mPresenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}

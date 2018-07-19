package com.proxy.mvp.demo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xia.mvp.MvpPresenter;
import com.xia.mvp.MvpView;

/**
 * @author xia
 * @date 2018/7/19.
 */
public abstract class BaseMvpActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView<P> {
    private P mPresent;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = newP();
        if (mPresent != null) {
            mPresent.attachView(this);
        }
    }

    @SuppressWarnings("unchecked")
    protected P getP() {
        if (mPresent == null) {
            mPresent = newP();
//            if (mPresent != null) {
//                mPresent.attachView(this);
//            }
        }
        return mPresent;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresent != null) {
            mPresent.detachView();
            mPresent.destroy();
            mPresent = null;
            Log.e("weixi", "onDestroy");
        }
    }
}

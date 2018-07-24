package com.proxy.mvp.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.proxy.mvp.demo.mvp.BaseMvpActivity;
import com.proxy.mvp.demo.mvp.TestPresenter;
import com.proxy.mvp.demo.mvp.TestView;

/**
 * @author xia
 * @date 2018/7/19.
 */
public class TestActivity extends BaseMvpActivity<TestPresenter> implements TestView {
    private AppCompatTextView mAppCompatTextView;

    public static void jump(Context context) {
        context.startActivity(new Intent(context, TestActivity.class));
    }

    @Override
    public TestPresenter newP() {
        return new TestPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        mAppCompatTextView = findViewById(R.id.example_user_id_tv);

//        getP().detachView();
//        getP().load();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getP().load();
            }
        }, 5000);
    }

    @Override
    public void showUserId(String userId) {
        Log.e("weixi", "userId:" + userId);
        mAppCompatTextView.setText(userId);
    }

    @Override
    public boolean isChecked() {
        return true;
    }
}

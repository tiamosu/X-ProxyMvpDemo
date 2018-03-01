package com.proxy.mvp.demo.example;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;

import com.proxy.mvp.demo.R;
import com.proxy.mvp.demo.mvp.BaseMvpActivity;

/**
 * @author xia
 * @date 2018/3/1.
 */

public class ExampleActivity extends BaseMvpActivity<ExamplePresent> implements IExampleView {
    private AppCompatTextView mUserIdTv;

    public static void jump(Context context) {
        context.startActivity(new Intent(context, ExampleActivity.class));
    }

    @Override
    public ExamplePresent newP() {
        return new ExamplePresent();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_example;
    }

    @Override
    public void initView() {
        mUserIdTv = (AppCompatTextView) findViewById(R.id.example_user_id_tv);
    }

    @Override
    public void loadData() {
        getP().load();
    }

    @Override
    public void showUserId(final String userId) {
        mUserIdTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mUserIdTv.setText(userId);
            }
        }, 10000);
    }
}

package com.proxy.mvp.demo.example;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

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
        mUserIdTv = findViewById(R.id.example_user_id_tv);
    }

    @Override
    public void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getP().load();
            }
        }, 5000);
    }

    @Override
    public void showUserId(final String userId) {
        Log.e("weixi", "userId:" + userId);
        mUserIdTv.setText(userId);
    }
}

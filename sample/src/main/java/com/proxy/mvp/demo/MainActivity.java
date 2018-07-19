package com.proxy.mvp.demo;

import android.view.View;

import com.proxy.mvp.demo.example.ExampleActivity;
import com.proxy.mvp.demo.mvp.BaseMvpActivity;
import com.proxy.mvp.demo.test.TestActivity;

public class MainActivity extends BaseMvpActivity {

    @Override
    public Object newP() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void loadData() {
    }

    @Override
    public void initEvent() {
        findViewById(R.id.main_jump_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ExampleActivity.jump(getContext());
                TestActivity.jump(getContext());
            }
        });
    }
}

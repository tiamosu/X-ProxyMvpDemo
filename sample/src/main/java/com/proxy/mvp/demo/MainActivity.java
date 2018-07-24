package com.proxy.mvp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.proxy.mvp.demo.mvp.BaseMvpActivity;

/**
 * @author xia
 */
public class MainActivity extends BaseMvpActivity {

    @Override
    public Object newP() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.main_jump_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.jump(MainActivity.this);
            }
        });
    }
}

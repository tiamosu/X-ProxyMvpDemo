package com.proxy.mvp.demo.example;

import com.proxy.mvp.demo.mvp.IView;

/**
 * @author xia
 * @date 2018/3/1.
 */

@SuppressWarnings("WeakerAccess")
public interface IExampleView extends IView<ExamplePresent> {

    void showUserId(String userId);
}

package com.proxy.mvp.demo.mvp;

/**
 * @author xia
 * @date 2018/7/19.
 */
public interface TestView extends BaseMvpView<TestPresenter> {

    void showUserId(String userId);

    boolean isChecked();
}

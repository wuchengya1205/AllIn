package com.xiang.allin.contract;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : fengzhangwei
 * date : 2019/9/11
 */
public interface LoginContract {

    interface IView extends IViewContract {

        void loginSuccess();

        void loginError(String msg);

        String getPhone();

        String getPassword();

    }

    interface IPresenter extends IPresenterContract {

        void login();
    }
}

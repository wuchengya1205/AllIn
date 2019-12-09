package com.xiang.allin.register.contract;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public interface RegisterContract {

    interface IView extends IViewContract {
        String getName();
        String getMobile();
        String location();
        String getAge();
        String getSex();
        String getBirthday();
        String getSign();
        String getEmail();
        String getPwd();
        void onError(String msg);
        void onSuccess();
    }

    interface IPresenter extends IPresenterContract {
        void verify();
    }
}

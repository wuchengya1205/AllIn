package com.xiang.allin.FirstPage.contract;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public interface MenuContract {

    interface IView extends IViewContract {
        void onError(String msg);
        void onSuccess();
    }

    interface IPresenter extends IPresenterContract {
        void updateUserInfo(String url);
    }
}

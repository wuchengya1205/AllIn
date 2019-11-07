package com.xiang.allin.FirstPage.contract;

import com.xiang.allin.common.CommonBean;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/30
 * time   :15:53
 * desc   :ohuo
 * version: 1.0
 */
public class InFirstPageContract {
    public interface IView extends IViewContract {

        void getDataSuccess(CommonBean commonBean);

        void getDataError(String msg);

        String getType();

        String getKey();
    }

    public interface IPresenter extends IPresenterContract {

        void getData();
    }
}

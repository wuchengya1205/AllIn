package com.xiang.allin.contract;

import com.xiang.allin.common.CommonBean;

import java.util.List;
import java.util.Map;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/22
 * time   :11:32
 * desc   :ohuo
 * version: 1.0
 */
public interface FirstPageContract {
    interface IView extends IViewContract {

        void getDataSuccess(CommonBean commonBean);

        void getDataError(String msg);

        Map<String,String> getParams();

        String getType();

        String getKey();
    }

    interface IPresenter extends IPresenterContract {

        void getData();
    }
}

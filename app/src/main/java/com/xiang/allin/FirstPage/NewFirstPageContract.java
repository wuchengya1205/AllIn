package com.xiang.allin.FirstPage;

import com.xiang.allin.common.CommonBean;

import java.util.Map;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/30
 * time   :10:52
 * desc   :ohuo
 * version: 1.0
 */
public class NewFirstPageContract {

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

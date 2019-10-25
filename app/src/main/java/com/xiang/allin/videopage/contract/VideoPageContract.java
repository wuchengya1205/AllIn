package com.xiang.allin.videopage.contract;

import com.xiang.allin.common.CommonBean;

import java.util.Map;

import mvp.ljb.kt.contract.IPresenterContract;
import mvp.ljb.kt.contract.IViewContract;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :15:00
 * desc   :ohuo
 * version: 1.0
 */
public interface VideoPageContract {
    interface IView extends IViewContract {


    }

    interface IPresenter extends IPresenterContract {

        void  getData();

    }
}

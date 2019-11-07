package com.xiang.allin.FirstPage.presenter;

import com.google.gson.Gson;
import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.FirstPage.contract.FirstPageContract;
import com.xiang.allin.utils.ThreadUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/22
 * time   :11:35
 * desc   :ohuo
 * version: 1.0
 */
public class FirstPagePresenter extends BaseMvpPresenter<FirstPageContract.IView> implements FirstPageContract.IPresenter {


    @Override
    public void getData() {

    }


}

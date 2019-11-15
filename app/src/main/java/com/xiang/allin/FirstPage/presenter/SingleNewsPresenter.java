package com.xiang.allin.FirstPage.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.xiang.allin.FirstPage.contract.NewsContract;
import com.xiang.allin.FirstPage.contract.SingleNewsContract;
import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.base.BaseObserver;
import com.xiang.allin.base.Constant;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.protocol.IHttpProtocol;

import net.ljb.kt.HttpConfig;
import net.ljb.kt.client.HttpFactory;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class SingleNewsPresenter extends BaseMvpPresenter<SingleNewsContract.IView> implements SingleNewsContract.IPresenter{
    @Override
    public void getNewsData() {
        Log.d("TAG","---URL----" + HttpConfig.INSTANCE.getBaseUrl());
        String type = getMvpView().getType();
        String key = "61005cfc63a8075c88d5d408ba90aff9";
        if (type.isEmpty()){
            getMvpView().onError("参数错误");
            getMvpView().dismissLoading();
            return;
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("key",key);
        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
                .getData(Constant.BASE_URL+"toutiao/index",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommonBean>(getMvpView()) {
                    @Override
                    protected void onNextEx(@NonNull CommonBean data) {
                        getMvpView().onSuccess(data);
                    }

                    @Override
                    protected void onErrorEx(@NonNull Throwable e) {
                        getMvpView().onError(e.toString());
                    }

                    @Override
                    protected void onNextSN(String msg) {
                        super.onNextSN(msg);
                        getMvpView().onError(msg);
                    }
                });
    }
}

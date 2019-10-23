package com.xiang.allin.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.base.BaseObserver;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.contract.FirstPageContract;
import com.xiang.allin.protocol.IHttpProtocol;

import net.ljb.kt.client.HttpFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/22
 * time   :11:35
 * desc   :ohuo
 * version: 1.0
 */
public class FirstPagePresenter extends BaseMvpPresenter<FirstPageContract.IView> implements  FirstPageContract.IPresenter {
    @Override
    public void getData() {
            getMvpView().showLoading();
            String type = getMvpView().getType();
            String key = getMvpView().getKey();
            if (type.isEmpty() || key.isEmpty()){
                getMvpView().getDataError("参数错误");
                getMvpView().dismissLoading();
                return;
            }
            getListDta(type,key);
    }

    private void getListDta(String type, String key) {
        Map<String,String> map = new HashMap<>();
        map.put("type",type);
        map.put("key",key);
        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
                .getData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<CommonBean>(getMvpView()) {
                    @Override
                    protected void onNextEx(@NonNull CommonBean data) {
                        getMvpView().getDataSuccess(data);
                    }

                    @Override
                    protected void onErrorEx(@NonNull Throwable e) {
                        getMvpView().getDataError(e.toString());
                    }
                });
    }
}

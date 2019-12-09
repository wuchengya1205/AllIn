package com.xiang.allin.FirstPage.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.xiang.allin.FirstPage.contract.MenuContract;
import com.xiang.allin.FirstPage.contract.NewsContract;
import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.base.BaseObserverTC;
import com.xiang.allin.protocol.IHttpProtocol;
import com.xiang.allin.videopage.bean.LoginBean;

import net.ljb.kt.client.HttpFactory;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class MenuPresenter extends BaseMvpPresenter<MenuContract.IView> implements MenuContract.IPresenter{

    @Override
    public void updateUserInfo(String url) {
        HashMap<String, String> map = new HashMap<>();
        map.put("imageUrl", url);
        map.put("uid", "776ec439-be0f-4df5-b492-39ac7cd360fd");
        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
                .updateUserInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverTC<String>(getMvpView()){

                    @Override
                    protected void onNextEx(@NonNull String data) {
                        Log.i("net","--------" + data);
                        getMvpView().onSuccess();
                    }

                    @Override
                    protected void onErrorEx(@NonNull Throwable e) {
                        if (e.toString().contains("ConnectException")){
                            getMvpView().onError("请检查网络连接!");
                        }else {
                            getMvpView().onError(e.toString());
                        }
                    }

                    @Override
                    protected void onNextSN(String msg) {
                        super.onNextSN(msg);
                        getMvpView().onError(msg);
                    }
                });
    }
}

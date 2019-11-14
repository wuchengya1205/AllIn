package com.xiang.allin.login;


import android.util.Log;
import androidx.annotation.NonNull;
import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.base.BaseObserver;
import com.xiang.allin.base.BaseObserverTC;
import com.xiang.allin.protocol.IHttpProtocol;
import com.xiang.allin.videopage.bean.LoginBean;

import net.ljb.kt.client.HttpFactory;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter extends BaseMvpPresenter<LoginContract.IView> implements LoginContract.IPresenter {

    @Override
    public void login() {
        getMvpView().showLoading();
        String phone = getMvpView().getPhone();
        String password = getMvpView().getPassword();
        if (phone.isEmpty() || password.isEmpty()) {
            getMvpView().loginError("账号或密码不能为空");
            getMvpView().dismissLoading();
            return;
        }
        loginNet(phone, password);
    }

    private void loginNet(String phone, String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", phone);
        map.put("password", password);
        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
                .login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserverTC<LoginBean>(getMvpView()){

                    @Override
                    protected void onNextEx(@NonNull LoginBean data) {
                        Log.i("net","--------" + data);
                        getMvpView().loginSuccess();
                    }

                    @Override
                    protected void onErrorEx(@NonNull Throwable e) {
                        if (e.toString().contains("ConnectException")){
                            getMvpView().loginError("请检查网络连接!");
                        }else {
                            getMvpView().loginError(e.toString());
                        }
                    }

                    @Override
                    protected void onNextSN(String msg) {
                        super.onNextSN(msg);
                        getMvpView().loginError(msg);
                    }
                });
    }
}

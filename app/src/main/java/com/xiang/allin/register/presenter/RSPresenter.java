package com.xiang.allin.register.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.base.BaseObserverTC;
import com.xiang.allin.protocol.IHttpProtocol;
import com.xiang.allin.register.contract.RegisterContract;
import com.xiang.allin.videopage.bean.LoginBean;

import net.ljb.kt.client.HttpFactory;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : fengzhangwei
 * date : 2019/11/22
 */
public class RSPresenter extends BaseMvpPresenter<RegisterContract.IView> implements RegisterContract.IPresenter {
    @Override
    public void verify() {
        String name = getMvpView().getName();
        String mobile = getMvpView().getMobile();
        String location = getMvpView().location();
        String age = getMvpView().getAge();
        String sex = getMvpView().getSex();
        String birthday = getMvpView().getBirthday();
        String sign = getMvpView().getSign();
        String email = getMvpView().getEmail();
        String pwd = getMvpView().getPwd();
        if (age.isEmpty() || sex.isEmpty() ||
                birthday.isEmpty() || name.isEmpty() ||
                mobile.isEmpty() || location.isEmpty() ||
                sign.isEmpty() || email.isEmpty() || pwd.isEmpty()) {
            getMvpView().onError("请填写相关信息");
            return;
        }
        register(name,mobile,location,age,sex,birthday,sign,email,pwd);
    }

    private void register(String name, String mobile, String location, String age, String sex, String birthday, String sign, String email, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username",name);
        map.put("mobile", mobile);
        map.put("age",age);
        map.put("sex",sex);
        map.put("imageUrl","https://www.baidu.com");
        map.put("location",location);
        map.put("birthday",birthday);
        map.put("password", pwd);
        map.put("sign",sign);
        map.put("email",email);
        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
                .register(map)
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

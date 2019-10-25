package com.xiang.allin.FirstPage.presenter;

import com.google.gson.Gson;
import com.xiang.allin.base.BaseMvpPresenter;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.FirstPage.FirstPageContract;
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
        final RequestBody requestBody = new FormBody.Builder()
                .add("type", type)
                .add("key",key)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request builder = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index")
                .post(requestBody)
                .build();
        okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getMvpView().getDataError(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final CommonBean commonBean = new Gson().fromJson(response.body().string(), CommonBean.class);
                    ThreadUtils.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            getMvpView().getDataSuccess(commonBean);
                        }
                    });
                }
            }
        });

//        HashMap<String,String> map = new HashMap<>();
//        map.put("type",type);
//        map.put("key",key);
//        HttpFactory.INSTANCE.getProtocol(IHttpProtocol.class)
//                .getData(map)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<CommonBean>(getMvpView()) {
//                    @Override
//                    protected void onNextEx(@NonNull CommonBean data) {
//                        getMvpView().getDataSuccess(data);
//                    }
//
//                    @Override
//                    protected void onErrorEx(@NonNull Throwable e) {
//                        getMvpView().getDataError(e.toString());
//                    }
//                });
    }
}

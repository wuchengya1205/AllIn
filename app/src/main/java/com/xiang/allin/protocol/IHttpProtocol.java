package com.xiang.allin.protocol;



import com.xiang.allin.base.BaseResponse;
import com.xiang.allin.base.BaseResponseTC;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.videopage.bean.LoginBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface IHttpProtocol {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseResponseTC<LoginBean>> login(@FieldMap HashMap<String, String> map);

    @POST("")
    @FormUrlEncoded
    Observable<BaseResponse<CommonBean>> getData(@Url String url,@FieldMap HashMap<String, String> map);
}

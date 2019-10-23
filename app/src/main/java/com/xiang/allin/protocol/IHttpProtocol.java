package com.xiang.allin.protocol;



import com.xiang.allin.BaseResponse;
import com.xiang.allin.common.CommonBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IHttpProtocol {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseResponse<String>> login(@FieldMap HashMap<String, String> map);

    @POST("toutiao/index")
    @FormUrlEncoded
    Observable<BaseResponse<CommonBean>> getData(@FieldMap Map<String,String> map);
}

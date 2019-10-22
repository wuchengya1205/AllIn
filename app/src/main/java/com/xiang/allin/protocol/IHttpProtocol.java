package com.xiang.allin.protocol;



import com.xiang.allin.BaseResponse;

import java.util.HashMap;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author : fengzhangwei
 * date : 2019/9/11
 */
public interface IHttpProtocol {
    @POST("login")
    @FormUrlEncoded
    Observable<BaseResponse<String>> login(@FieldMap HashMap<String, String> map);
}

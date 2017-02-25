package com.hujie.fazhiregister.net;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hujie on 2017/2/22.
 */

public interface IApi {

    @POST("sendRegCode")
    Observable<BaseBean<String>> sendRegCode(@Body RequestBody body);

    @POST("regUser")
    Observable<BaseBean<String>> regUser(@Body RequestBody body);

    @POST("userLogin")
    Observable<BaseBean<UserBean>> userLogin(@Body RequestBody body);

    @POST("getUser")
    Observable<BaseBean<UserBean>> getUser(@Body RequestBody body);

    @POST("updateUser")
    Observable<BaseBean<UserBean>> updateUser(@Body RequestBody body);
}

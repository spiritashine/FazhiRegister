package com.hujie.fazhiregister.utils;

import com.hujie.fazhiregister.App;

/**
 * Created by wanggang on 2017/2/22.
 */

public class LocalData {
    public static String getSessionKey() {
        return (String)SPUtils.get(App.getApp(),"SessionKey","");
    }

    public static void putSessionKey(String SessionKey){
        SPUtils.put(App.getApp(),"SessionKey",SessionKey);
    }

    public static int getUserId() {
        return (int) SPUtils.get(App.getApp(),"user_id",0);
    }

    public static void putUserId(int UserId){
        SPUtils.put(App.getApp(),"user_id",UserId);
    }


}

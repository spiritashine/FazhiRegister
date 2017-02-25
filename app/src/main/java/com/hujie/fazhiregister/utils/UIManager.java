package com.hujie.fazhiregister.utils;

import android.content.Context;
import android.content.Intent;

import com.hujie.fazhiregister.LogInActivity;
import com.hujie.fazhiregister.UserInfoActivity;

/**
 * Created by hujie on 2017/2/23.
 */

public class UIManager {
    public static void getLogIn(Context context){
        context.startActivity(new Intent(context, LogInActivity.class));
    }

    public static void getUserInfo(Context context){
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }
}

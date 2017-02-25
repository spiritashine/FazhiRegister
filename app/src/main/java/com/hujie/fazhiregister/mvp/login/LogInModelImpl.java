package com.hujie.fazhiregister.mvp.login;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;
import com.hujie.fazhiregister.net.NetUtil;

import java.util.HashMap;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by hujie on 2017/2/22.
 */

public class LogInModelImpl implements ILogInModel {
    @Override
    public Observable<BaseBean<String>> getCode(String phone) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("mobile",phone);
        RequestBody codeBody = NetUtil.getCodeBody(map);
        return NetUtil.getInstance().getIApi().sendRegCode(codeBody);
    }

    @Override
    public Observable<BaseBean<String>> getReg(String phone, String password, String code) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("mobile",phone);
        map.put("password",password);
        map.put("verify_code",code);
        RequestBody codeBody = NetUtil.getCodeBody(map);
        return NetUtil.getInstance().getIApi().regUser(codeBody);
    }

    @Override
    public Observable<BaseBean<UserBean>> getLogIn(String name, String password) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("username",name);
        map.put("password",password);
        RequestBody codeBody = NetUtil.getCodeBody(map);
        return NetUtil.getInstance().getIApi().userLogin(codeBody);
    }
}

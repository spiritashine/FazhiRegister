package com.hujie.fazhiregister.mvp.login;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;

import rx.Observable;

/**
 * Created by hujie on 2017/2/22.
 */

public interface ILogInModel {

    Observable<BaseBean<String>> getCode(String phone);

    Observable<BaseBean<String>> getReg(String phone,String password,String code);

    Observable<BaseBean<UserBean>> getLogIn(String name,String password);
}

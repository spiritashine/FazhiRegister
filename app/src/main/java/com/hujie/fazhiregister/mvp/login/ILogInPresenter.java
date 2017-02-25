package com.hujie.fazhiregister.mvp.login;

/**
 * Created by hujie on 2017/2/22.
 */

public interface ILogInPresenter {

    void getCode(String phone);

    void getReg(String phone,String password,String code);

    void logIn(String name,String password);
}

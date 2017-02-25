package com.hujie.fazhiregister.mvp.getuser;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;

import rx.Observable;

/**
 * Created by hujie on 2017/2/23.
 */

public interface IUserInfoPresenter {
    void getUserInfo();
    void updateUserInfo(UserBean userBean);
}

package com.hujie.fazhiregister.mvp.getuser;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;

import rx.Observable;

/**
 * Created by hujie on 2017/2/23.
 */

public interface IUserInfoModel {
    Observable<BaseBean<UserBean>> getUser(int userId);
    Observable<BaseBean<UserBean>> UpdateUser(String nickname,
                                              String icon,
                                              int gender,
                                              int age,
                                              String province,
                                              String city,
                                              String trade,
                                              String company,
                                              String job,
                                              String fullname);
}

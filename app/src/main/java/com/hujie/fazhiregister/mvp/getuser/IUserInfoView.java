package com.hujie.fazhiregister.mvp.getuser;

import com.hujie.fazhiregister.base.BaseView;
import com.hujie.fazhiregister.base.UserBean;

/**
 * Created by hujie on 2017/2/23.
 */

public interface IUserInfoView extends BaseView {
    void showUserData(UserBean userBean);
}

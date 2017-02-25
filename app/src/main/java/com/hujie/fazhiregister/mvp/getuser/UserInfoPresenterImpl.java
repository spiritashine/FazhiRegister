package com.hujie.fazhiregister.mvp.getuser;

import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.BaseView;
import com.hujie.fazhiregister.base.UserBean;
import com.hujie.fazhiregister.net.NetSubscriber;
import com.hujie.fazhiregister.net.RxHelper;
import com.hujie.fazhiregister.utils.LocalData;

import rx.Observable;

/**
 * Created by hujie on 2017/2/23.
 */

public class UserInfoPresenterImpl implements IUserInfoPresenter{
    private IUserInfoModel model;
    private IUserInfoView view;

    public UserInfoPresenterImpl(IUserInfoView view) {
        this.view = view;
        model = new UserInfoModelImpl();
    }

    @Override
    public void getUserInfo() {
        int userId = LocalData.getUserId();
        Observable<BaseBean<UserBean>> user = model.getUser(userId);
        user.compose(RxHelper.transform()).compose(RxHelper.schedulers())
                .subscribe(new NetSubscriber<UserBean>() {
            @Override
            public BaseView getView() {
                return view;
            }

            @Override
            public void onNext(UserBean userBean) {
                view.showUserData(userBean);
            }
        });
    }

    @Override
    public void updateUserInfo(final UserBean user) {
        Observable<BaseBean<UserBean>> observable = model.UpdateUser(user.getNickname(),
                user.getIcon(),
                user.getGender(),
                user.getAge(),
                user.getProvince(),
                user.getCity(),
                user.getTrade(),
                user.getCompany(),
                user.getJob(),
                user.getFullname());
        observable.compose(RxHelper.schedulers()).compose(RxHelper.transform()).subscribe(new NetSubscriber<UserBean>() {
            @Override
            public BaseView getView() {
                return view;
            }

            @Override
            public void onNext(UserBean user) {
                view.showError("完成");
            }
        });
    }
}

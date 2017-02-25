package com.hujie.fazhiregister.mvp.login;

import android.text.TextUtils;

import com.hujie.fazhiregister.base.BaseView;
import com.hujie.fazhiregister.base.BaseBean;
import com.hujie.fazhiregister.base.UserBean;
import com.hujie.fazhiregister.net.NetConfig;
import com.hujie.fazhiregister.net.NetSubscriber;
import com.hujie.fazhiregister.net.RxHelper;
import com.hujie.fazhiregister.utils.LocalData;
import com.hujie.fazhiregister.utils.UIManager;

import rx.Observable;

/**
 * Created by hujie on 2017/2/22.
 */

public class LogInPresenterImpl implements ILogInPresenter {
    private ILogInModel model;
    private ILogInView view;

    public LogInPresenterImpl(ILogInView view) {
        this.view = view;
        model = new LogInModelImpl();
    }

    @Override
    public void getCode(String phone) {
        if (TextUtils.isEmpty(phone)){
            view.showHint("手机号码不能为空");
            return;
        }
        if (phone.length()!=11){
            view.showHint("手机号码格式错误");
            return;
        }
        Observable<BaseBean<String>> code = model.getCode(phone);
        code.compose(RxHelper.transform()).compose(RxHelper.schedulers()).subscribe(new NetSubscriber() {
            @Override
            public BaseView getView() {
                return view;
            }

            @Override
            public void onNext(Object o) {
                view.showHint("已发送");
            }
        });
    }

    @Override
    public void getReg(String phone, String password, String code) {
        if (TextUtils.isEmpty(phone)){
            view.showHint("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)){
            view.showHint("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)){
            view.showHint("验证码不能为空");
            return;
        }
        Observable<BaseBean<String>> modelReg = model.getReg(phone, NetConfig.MD5(password), code);
        modelReg.compose(RxHelper.transform()).compose(RxHelper.schedulers()).subscribe(new NetSubscriber() {
            @Override
            public BaseView getView() {
                return view;
            }

            @Override
            public void onNext(Object o) {
                view.showHint("注册成功");
                UIManager.getLogIn(view.getContext());
            }
        });
    }

    @Override
    public void logIn(String name, String password) {
        Observable<BaseBean<UserBean>> logIn = model.getLogIn(name, NetConfig.MD5(password));
        logIn.compose(RxHelper.transform()).compose(RxHelper.schedulers()).subscribe(new NetSubscriber<UserBean>() {
            @Override
            public BaseView getView() {
                return view;
            }

            @Override
            public void onNext(UserBean userBean) {
                LocalData.putSessionKey(userBean.getSession_key());
                LocalData.putUserId(userBean.getUser_id());
                UIManager.getUserInfo(view.getContext());
            }
        });
    }
}

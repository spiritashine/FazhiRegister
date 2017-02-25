package com.hujie.fazhiregister.net;


import com.hujie.fazhiregister.base.BaseView;

import rx.Subscriber;

/**
 * Created by wanggang on 2017/2/22.
 */

public abstract class  NetSubscriber<T> extends Subscriber<T> {

    private BaseView view;

    @Override
    public void onStart() {
        super.onStart();
        view = getView();
        view.showLoading();
    }

    @Override
    public void onCompleted() {
        view.hideLoading();
    }


    @Override
    public void onError(Throwable e) {
        view.hideLoading();
        ExceptionHandle.ResponeThrowable exception;
        if(e instanceof ExceptionHandle.ResponeThrowable){
            exception= (ExceptionHandle.ResponeThrowable) e;
        }else {
            exception = ExceptionHandle.handleException(e);
        }
        view.showError(exception.message);
    }


    public abstract BaseView getView();
}

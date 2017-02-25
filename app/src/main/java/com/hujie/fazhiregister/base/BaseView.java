package com.hujie.fazhiregister.base;

import android.content.Context;

/**
 * Created by hujie on 2017/2/22.
 */

public interface BaseView {
    void showLoading();
    void hideLoading();
    void showError(String error);
    Context getContext();
}

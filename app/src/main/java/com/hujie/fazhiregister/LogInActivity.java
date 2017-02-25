package com.hujie.fazhiregister;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.hujie.fazhiregister.mvp.login.ILogInView;
import com.hujie.fazhiregister.utils.LoadingDialog;
import com.hujie.fazhiregister.mvp.login.LogInPresenterImpl;
import com.hujie.fazhiregister.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends AppCompatActivity implements ILogInView{

    @BindView(R.id.login_et_phone)
    EditText loginEtPhone;
    @BindView(R.id.login_et_pasd)
    EditText loginEtPasd;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    private LogInPresenterImpl presenter;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
        presenter = new LogInPresenterImpl(this);
    }

    @OnClick(R.id.login_btn_login)
    public void onClick() {
        presenter.logIn(loginEtPhone.getText().toString(),loginEtPasd.getText().toString());
    }

    @Override
    public void showHint(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void showLoading() {
        dialog = new LoadingDialog(this);
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showError(String error) {
        ToastUtils.showErrorToast(this,error);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

package com.hujie.fazhiregister;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hujie.fazhiregister.mvp.login.ILogInView;
import com.hujie.fazhiregister.utils.LoadingDialog;
import com.hujie.fazhiregister.mvp.login.LogInPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ILogInView{

    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.get_vertifycode)
    Button getVertifycode;
    @BindView(R.id.register)
    Button register;
    private LogInPresenterImpl presenter;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new LogInPresenterImpl(this);
    }

    @OnClick({R.id.get_vertifycode, R.id.register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_vertifycode:
                presenter.getCode(registerPhone.getText().toString());
                break;
            case R.id.register:
                presenter.getReg(registerPhone.getText().toString(),registerPassword.getText().toString(),code.getText().toString());
                break;
        }
    }

    @Override
    public void showHint(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_LONG).show();
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
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}

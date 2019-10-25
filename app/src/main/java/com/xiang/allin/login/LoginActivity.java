package com.xiang.allin.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiang.allin.FirstPage.MainActivity;
import com.xiang.allin.R;
import com.xiang.allin.base.ac.BaseMvpActivity;

import org.jetbrains.annotations.NotNull;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :18:00
 * desc   :ohuo
 * version: 1.0
 */
public class LoginActivity extends BaseMvpActivity<LoginContract.IPresenter> implements LoginContract.IView, View.OnClickListener {

    private EditText mobile;
    private EditText password;
    private Button btn_login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        goActivity(MainActivity.class);
        finish();
    }

    @Override
    public void loginError(String msg) {
        showToast(msg);
    }


    @Override
    public String getPhone() {
        return mobile.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @NotNull
    @Override
    public Class<? extends LoginContract.IPresenter> registerPresenter() {
        return LoginPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        mobile = findViewById(R.id.ed_mobile);
        password = findViewById(R.id.ed_pwd);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    public void initData() {
        super.initData();
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            //            getPresenter().login();
            goActivity(MainActivity.class);
        }
    }
}
package com.xiang.allin.register;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.xiang.allin.R;
import com.xiang.allin.base.ac.BaseActivity;
import com.xiang.allin.base.ac.BaseMvpActivity;
import com.xiang.allin.register.contract.RegisterContract;
import com.xiang.allin.register.presenter.RSPresenter;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends BaseMvpActivity<RegisterContract.IPresenter> implements View.OnClickListener, RegisterContract.IView {


    private EditText ed_name;
    private EditText ed_mobile;
    private EditText ed_location;
    private EditText ed_age;
    private EditText ed_sex;
    private EditText ed_birthday;
    private EditText ed_sign;
    private EditText ed_email;
    private EditText ed_password;
    private Button btn_rs;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        super.initView();
        ed_name = findViewById(R.id.ed_name);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_location = findViewById(R.id.ed_location);
        ed_age = findViewById(R.id.ed_age);
        ed_sex = findViewById(R.id.ed_sex);
        ed_birthday = findViewById(R.id.ed_birthday);
        ed_sign = findViewById(R.id.ed_sign);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_rs = findViewById(R.id.btn_register);
    }

    @Override
    public void initData() {
        super.initData();
        btn_rs.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        showLoading();
        if (v.getId() == R.id.btn_register){
            getPresenter().verify();
        }
    }

    @Override
    public String getName() {
        return ed_name.getText().toString().trim();
    }

    @Override
    public String getMobile() {
        return ed_mobile.getText().toString().trim();
    }

    @Override
    public String location() {
        return ed_location.getText().toString().trim();
    }

    @Override
    public String getAge() {
        return ed_age.getText().toString().trim();
    }

    @Override
    public String getSex() {
        return ed_sex.getText().toString().trim();
    }

    @Override
    public String getBirthday() {
        return ed_birthday.getText().toString().trim();
    }

    @Override
    public String getSign() {
        return ed_sign.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return ed_email.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return ed_password.getText().toString().trim();
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
    }

    @Override
    public void onSuccess() {
        showToast("注册成功");
        finish();
    }

    @NotNull
    @Override
    public Class<? extends RegisterContract.IPresenter> registerPresenter() {
        return RSPresenter.class;
    }
}

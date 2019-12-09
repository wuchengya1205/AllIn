package com.xiang.allin.login;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.xiang.allin.FirstPage.AccessActivity;
import com.xiang.allin.FirstPage.MainActivity;
import com.xiang.allin.R;
import com.xiang.allin.application.AllInApplication;
import com.xiang.allin.base.Constant;
import com.xiang.allin.base.ac.BaseMvpActivity;
import com.xiang.allin.register.RegisterActivity;

import net.ljb.kt.HttpConfig;

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
    private TextView tv_register;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        goActivity(AccessActivity.class);
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
        tv_register = findViewById(R.id.tv_register);
    }

    @Override
    public void initData() {
        super.initData();
        HttpConfig.INSTANCE.init(Constant.BASE_TOMACT_URL, AllInApplication.getHeader(),AllInApplication.getParams(),true);
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login){
            getPresenter().login();
        }
        if (v.getId() == R.id.tv_register){
            goActivity(RegisterActivity.class);
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initBar() {
        ImmersionBar.with(this)
                .transparentStatusBar()  //透明状态栏，不写默认透明色
                .hideBar(BarHide.FLAG_SHOW_BAR)
                .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为 true)
                .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为 true）
                .statusBarAlpha(0.3f)  //状态栏透明度，不写默认 0.0f
                .navigationBarAlpha(0.2f)  //导航栏透明度，不写默认 0.0F
                .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认 0.0f
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();
    }
}
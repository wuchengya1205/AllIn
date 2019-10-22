package com.xiang.allin.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;


public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {

    }

    private void init(Bundle savedInstanceState) {

    }

    protected void goActivity(Class cls){
        goActivity(cls,null);
    }

    protected void goActivity(Class cls,Bundle bundle){
        Intent intent = new Intent(this, cls);
        if (bundle != null){
            intent.putExtra("bundle",bundle);
        }
        startActivity(intent);
    }


    protected abstract int getLayoutId();
}

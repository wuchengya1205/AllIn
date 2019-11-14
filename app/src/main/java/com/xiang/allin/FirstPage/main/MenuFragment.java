package com.xiang.allin.FirstPage.main;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.xiang.allin.FirstPage.AccessActivity;
import com.xiang.allin.FirstPage.MainActivity;
import com.xiang.allin.FirstPage.contract.MenuContract;
import com.xiang.allin.FirstPage.presenter.MenuPresenter;
import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;

import org.jetbrains.annotations.NotNull;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class MenuFragment extends BaseMvpFragment<MenuContract.IPresenter> implements MenuContract.IView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView iv_close;
    private ImageView iv_voice;
    private ImageView iv_shake;
    private Switch sw_voice;
    private Switch sw_shake;

    @Override
    protected int getLayoutId() {
        return R.layout.navigationview_head;
    }

    @NotNull
    @Override
    public Class<? extends MenuContract.IPresenter> registerPresenter() {
        return MenuPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        iv_close = view.findViewById(R.id.iv_close);
        iv_voice = view.findViewById(R.id.iv_voice);
        iv_shake = view.findViewById(R.id.iv_shake);
        sw_voice = view.findViewById(R.id.sw_voice);
        sw_shake = view.findViewById(R.id.sw_shake);
    }

    @Override
    public void initData() {
        super.initData();
        iv_close.setOnClickListener(this);
        sw_voice.setOnCheckedChangeListener(this);
        sw_shake.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close){
            AccessActivity.openHome();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_voice){
            if (isChecked) {
                sw_voice.setSwitchTextAppearance(getContext(),R.style.s_true);
                iv_voice.setImageResource(R.mipmap.icon_voice_open);
            }else {
                sw_voice.setSwitchTextAppearance(getContext(),R.style.s_false);
                iv_voice.setImageResource(R.mipmap.icon_voice_close);
            }
        }
        if (buttonView.getId() == R.id.sw_shake){
            if (isChecked) {
                sw_shake.setSwitchTextAppearance(getContext(),R.style.s_true);
                iv_shake.setImageResource(R.mipmap.icon_shake_open);
            }else {
                sw_shake.setSwitchTextAppearance(getContext(),R.style.s_false);
                iv_shake.setImageResource(R.mipmap.icon_shake_close);
            }
        }
    }
}

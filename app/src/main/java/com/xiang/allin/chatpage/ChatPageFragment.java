package com.xiang.allin.chatpage;

import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.chatpage.contract.ChatPageContract;
import com.xiang.allin.chatpage.presenter.ChatPagePresenter;

import org.jetbrains.annotations.NotNull;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :15:06
 * desc   :ohuo
 * version: 1.0
 */
public class ChatPageFragment extends BaseMvpFragment<ChatPageContract.IPresenter> implements ChatPageContract.IView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @NotNull
    @Override
    public Class<? extends ChatPageContract.IPresenter> registerPresenter() {
        return ChatPagePresenter.class;
    }
}

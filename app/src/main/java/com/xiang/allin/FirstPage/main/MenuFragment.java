package com.xiang.allin.FirstPage.main;

import com.xiang.allin.FirstPage.contract.MenuContract;
import com.xiang.allin.FirstPage.contract.NewsContract;
import com.xiang.allin.FirstPage.presenter.MenuPresenter;
import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;

import org.jetbrains.annotations.NotNull;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class MenuFragment extends BaseMvpFragment<MenuContract.IPresenter> implements MenuContract.IView{
    @Override
    protected int getLayoutId() {
        return R.layout.navigationview_head;
    }

    @NotNull
    @Override
    public Class<? extends MenuContract.IPresenter> registerPresenter() {
        return MenuPresenter.class;
    }
}

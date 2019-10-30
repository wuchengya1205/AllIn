package com.xiang.allin.FirstPage.fr;

import com.xiang.allin.FirstPage.FirstPageContract;
import com.xiang.allin.FirstPage.NewFirstPageContract;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.common.CommonBean;

import org.jetbrains.annotations.NotNull;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/30
 * time   :10:51
 * desc   :ohuo
 * version: 1.0
 */
public class NewFirstPageFragment extends BaseMvpFragment<NewFirstPageContract.IPresenter> implements NewFirstPageContract.IView{
    @Override
    public void getDataSuccess(CommonBean commonBean) {

    }

    @Override
    public void getDataError(String msg) {

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getKey() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @NotNull
    @Override
    public Class<? extends NewFirstPageContract.IPresenter> registerPresenter() {
        return null;
    }
}

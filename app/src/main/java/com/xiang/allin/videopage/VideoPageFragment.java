package com.xiang.allin.videopage;

import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.videopage.contract.VideoPageContract;
import com.xiang.allin.videopage.presenter.VideoPagePresenter;

import org.jetbrains.annotations.NotNull;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :14:57
 * desc   :ohuo
 * version: 1.0
 */
public class VideoPageFragment extends BaseMvpFragment<VideoPageContract.IPresenter> implements VideoPageContract.IView {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @NotNull
    @Override
    public Class<? extends VideoPageContract.IPresenter> registerPresenter() {
        return VideoPagePresenter.class;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}

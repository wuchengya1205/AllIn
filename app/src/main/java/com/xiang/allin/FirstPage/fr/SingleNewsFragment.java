package com.xiang.allin.FirstPage.fr;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiang.allin.FirstPage.adapter.RecyclerListAdapter;
import com.xiang.allin.FirstPage.contract.NewsContract;
import com.xiang.allin.FirstPage.contract.SingleNewsContract;
import com.xiang.allin.FirstPage.presenter.SingleNewsPresenter;
import com.xiang.allin.R;
import com.xiang.allin.application.AllInApplication;
import com.xiang.allin.base.Constant;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.listener.RefreshListener;

import net.ljb.kt.HttpConfig;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class SingleNewsFragment extends BaseMvpFragment<SingleNewsContract.IPresenter> implements SingleNewsContract.IView, RefreshListener.top{


    public static String TAB_KEY = "tabType";
    private RecyclerView mRecyclerView;
    private String tabType;
    private RecyclerListAdapter recyclerListAdapter;
    boolean mIsPrepare = false;		//视图还没准备好
    boolean mIsVisible= false;		//不可见
    boolean mIsFirstLoad = true;	//第一次加载

    @Override
    protected int getLayoutId() {
        return R.layout.layout_news;
    }

    @NotNull
    @Override
    public Class<? extends SingleNewsContract.IPresenter> registerPresenter() {
        return SingleNewsPresenter.class;
    }

    @Override
    public void initView() {
        super.initView();
        mRecyclerView = view.findViewById(R.id.rl_news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mIsPrepare = true;

    }

    @Override
    public void initData() {
        super.initData();
        tabType = getArguments().getString(TAB_KEY);
        recyclerListAdapter = new RecyclerListAdapter(getContext(), new ArrayList<CommonBean.DataBean>());
        mRecyclerView.setAdapter(recyclerListAdapter);
        NewsFragment.setOnRefreshLoadMoreListener(this);
        lazyLoad();
    }

    @Override
    public String getType() {
        return tabType;
    }

    @Override
    public void onError(String msg) {
        showToast(msg);
        NewsFragment.finishNo();
    }

    @Override
    public void onSuccess(CommonBean commonBean) {
        if ("1".equals(commonBean.getStat())){
            recyclerListAdapter.setData(commonBean.getData());
            List<CommonBean.DataBean> data = recyclerListAdapter.getData();
            NewsFragment.finish();
            Log.d("TAG","----size---" + data.size());
        }
    }

    @Override
    public void refresh() {
        getPresenter().getNewsData();
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            mIsVisible = true;
            if (getView() != null){
                lazyLoad();
            }
        } else {
            mIsVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行三个条件的判断，如果有一个不满足，都将不进行加载
        if (!mIsPrepare || !mIsVisible|| !mIsFirstLoad) {
            return;
        }
        loadData();
        //数据加载完毕,恢复标记,防止重复加载
        mIsFirstLoad = false;
    }

    private void loadData() {
        getPresenter().getNewsData();
    }
}
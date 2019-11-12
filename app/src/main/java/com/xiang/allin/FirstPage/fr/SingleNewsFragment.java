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
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.common.CommonBean;
import com.xiang.allin.listener.RefreshListener;

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
    }

    @Override
    public void initData() {
        super.initData();
        tabType = getArguments().getString(TAB_KEY);
        recyclerListAdapter = new RecyclerListAdapter(getContext(), new ArrayList<CommonBean.DataBean>());
        mRecyclerView.setAdapter(recyclerListAdapter);
        NewsFragment.setOnRefreshLoadMoreListener(this);

    }

    @Override
    public String getType() {
        return tabType;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getNewsData();
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
}

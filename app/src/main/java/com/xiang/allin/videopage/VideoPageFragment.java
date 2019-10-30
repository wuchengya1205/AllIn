package com.xiang.allin.videopage;

import android.net.Uri;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiang.allin.R;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.videopage.adapter.VideoListAdapter;
import com.xiang.allin.videopage.contract.VideoPageContract;
import com.xiang.allin.videopage.presenter.VideoPagePresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/10/24
 * time   :14:57
 * desc   :ohuo
 * version: 1.0
 */
public class VideoPageFragment extends BaseMvpFragment<VideoPageContract.IPresenter> implements VideoPageContract.IView {

    private RecyclerView video_list;
    private List<String> videoList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initView() {
        super.initView();
        video_list = getActivity().findViewById(R.id.video_list);

    }

    @Override
    public void initData() {
        super.initData();
        videoList.add("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fd30000befl25pcgf3dsrv6mmog&line=0");
        videoList.add("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fa90000bee4qrqr863tt0o22t50&line=0");
        videoList.add("https://oimryzjfe.qnssl.com/content/fe9cfd1402bb40490bc9a208db7c0921.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/47465d359406bb4b68c8c205e2974807.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/93fcbd491e40159e949bb4cb191e231e.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/beee1c9325330b845b13298842f711ff.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/807403BE56FD9503A609975B81BA4636.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/68239E7D6DC93D98E083137F0C537D97.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/afc192cfae2df1366d7268bc7a181555.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/2c61c7c5e95b3f4dec31aa42e4315bb1.mp4");
        videoList.add("https://oimryzjfe.qnssl.com/content/0fcbbe738abf1bf524dc2e7818200cc8.mp4");
        video_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity(), videoList);
        video_list.setAdapter(videoListAdapter);
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

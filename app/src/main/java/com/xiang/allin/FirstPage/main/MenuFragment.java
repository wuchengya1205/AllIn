package com.xiang.allin.FirstPage.main;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.coder.circlebar.CircleBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.xiang.allin.FirstPage.AccessActivity;
import com.xiang.allin.FirstPage.MainActivity;
import com.xiang.allin.FirstPage.contract.MenuContract;
import com.xiang.allin.FirstPage.presenter.MenuPresenter;
import com.xiang.allin.R;
import com.xiang.allin.base.LoadingDialog;
import com.xiang.allin.base.fr.BaseMvpFragment;
import com.xiang.allin.utils.FileUpLoadManager;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author : fengzhangwei
 * date : 2019/11/12
 */
public class MenuFragment extends BaseMvpFragment<MenuContract.IPresenter> implements MenuContract.IView, View.OnClickListener, CompoundButton.OnCheckedChangeListener, FileUpLoadManager.FileUpLoadCallBack {

    private ImageView iv_close;
    private ImageView iv_voice;
    private ImageView iv_shake;
    private Switch sw_voice;
    private Switch sw_shake;
    private ImageView iv_icon;
    private FileUpLoadManager fileUpLoadManager;


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
        iv_icon = view.findViewById(R.id.iv_icon);
        iv_voice = view.findViewById(R.id.iv_voice);
        iv_shake = view.findViewById(R.id.iv_shake);
        sw_voice = view.findViewById(R.id.sw_voice);
        sw_shake = view.findViewById(R.id.sw_shake);

    }

    @Override
    public void initData() {
        super.initData();
        fileUpLoadManager = new FileUpLoadManager();
        iv_close.setOnClickListener(this);
        iv_icon.setOnClickListener(this);
        sw_voice.setOnCheckedChangeListener(this);
        sw_shake.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_close) {
            AccessActivity.openHome();
        }
        if (v.getId() == R.id.iv_icon) {
            getPictureImage();
        }
    }

    private void getPictureImage() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(10)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.sw_voice) {
            if (isChecked) {
                sw_voice.setSwitchTextAppearance(getContext(), R.style.s_true);
                iv_voice.setImageResource(R.mipmap.icon_voice_open);
            } else {
                sw_voice.setSwitchTextAppearance(getContext(), R.style.s_false);
                iv_voice.setImageResource(R.mipmap.icon_voice_close);
            }
        }
        if (buttonView.getId() == R.id.sw_shake) {
            if (isChecked) {
                sw_shake.setSwitchTextAppearance(getContext(), R.style.s_true);
                iv_shake.setImageResource(R.mipmap.icon_shake_open);
            } else {
                sw_shake.setSwitchTextAppearance(getContext(), R.style.s_false);
                iv_shake.setImageResource(R.mipmap.icon_shake_close);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ArrayList<String> sList = new ArrayList<>();
        List<LocalMedia> images = PictureSelector.obtainMultipleResult(data);
        if (images.size() > 0) {
            if (resultCode == getActivity().RESULT_OK) {
                if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                    showUpLoading();
                    Log.d("TAG", "选择回调");
                    for (int i = 0; i < images.size(); i++) {
                        String path = images.get(i).getPath();
                        sList.add(path);
                    }
                    setUpLoadCount(sList.size());
                    fileUpLoadManager.upLoadFile(sList, this);
                }
            }
        }

    }

    @Override
    public void onError(Throwable e) {
        Log.d("TAG", "上传失败====" + e.toString());
        dismissUpLoading();
    }

    @Override
    public void onSuccess(String url) {
        Log.d("TAG", "上传成功====" + url);
        dismissUpLoading();
    }

    @Override
    public void onProgress(int pro, int position) {
        updatePb((position + 1), pro);
    }
}

package com.xiang.allin.FirstPage.fr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.xiang.allin.R;

/**
 * author : wuchengya
 * e-mail : wucy1205@yeah.net
 * date   : 2019/11/11
 * time   :16:46
 * desc   :ohuo
 * version: 1.0
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_infirstpage, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(),"加载完了",Toast.LENGTH_LONG).show();
    }
}

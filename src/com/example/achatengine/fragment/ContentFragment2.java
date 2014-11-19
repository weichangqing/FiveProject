package com.example.achatengine.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.achatengine.R;

public class ContentFragment2 extends Fragment {

    private static final String TAG = "ListFragmentImpl";

    private ListView selfList;

    String[] cities = { "Shenzhen", "Beijing", "Shanghai", "Guangzhou", "Wuhan", "Tianjing", "Changsha", "Xi'an",
            "Chongqing", "Guilin", };

    private Activity activity;

    @SuppressLint("NewApi")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.content, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_content);
        tv.setText("我是第二个fragment");
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // 设置ListFragment默认的ListView，即@id/android:list

    }

}

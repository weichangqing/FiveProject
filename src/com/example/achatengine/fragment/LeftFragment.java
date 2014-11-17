package com.example.achatengine.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.achatengine.R;

public class LeftFragment extends ListFragment {

    private static final String TAG = "ListFragmentImpl";

    private ListView selfList;

    String[] cities = { "Shenzhen", "Beijing", "Shanghai", "Guangzhou", "Wuhan", "Tianjing", "Changsha", "Xi'an" };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        return inflater.inflate(R.layout.list, container, false);
    }

    OnArticleSelectedListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnArticleSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implementOnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        // 设置ListFragment默认的ListView，即@id/android:list
        this.setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cities));

    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        mListener.onArticleSelected(position);
    }

    public interface OnArticleSelectedListener {
        public void onArticleSelected(int position);
    }

}

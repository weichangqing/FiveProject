package com.example.achatengine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.example.achatengine.fragment.ContentFragment1;
import com.example.achatengine.fragment.ContentFragment2;
import com.example.achatengine.fragment.ContentFragment3;

public class Main5Activity extends FragmentActivity implements OnClickListener {

    private LinearLayout left, content;
    private FragmentManager fragmentManager;
    private LinearLayout ll1, ll2, ll3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        left = (LinearLayout) findViewById(R.id.left);
        content = (LinearLayout) findViewById(R.id.content);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ContentFragment1 fragmentcontent = new ContentFragment1();
        fragmentTransaction.add(R.id.content, fragmentcontent);
        fragmentTransaction.commit();
        initLeftView(1);
    }

    private void initLeftView(int position) {
        ll1.setBackgroundColor(Color.parseColor("#737373"));
        ll2.setBackgroundColor(Color.parseColor("#737373"));
        ll3.setBackgroundColor(Color.parseColor("#737373"));
        switch (position) {
        case 1:
            ll1.setBackgroundColor(Color.parseColor("#8c8c8c"));
            break;

        case 2:
            ll2.setBackgroundColor(Color.parseColor("#8c8c8c"));
            break;
        case 3:
            ll3.setBackgroundColor(Color.parseColor("#8c8c8c"));
            break;
        default:
            break;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
        case R.id.ll1:
            initLeftView(1);
            ContentFragment1 content1 = new ContentFragment1();
            fragmentTransaction.replace(R.id.content, content1);
            fragmentTransaction.commit();
            break;
        case R.id.ll2:
            initLeftView(2);
            ContentFragment2 content2 = new ContentFragment2();
            fragmentTransaction.replace(R.id.content, content2);
            fragmentTransaction.commit();
            break;
        case R.id.ll3:
            initLeftView(3);
            ContentFragment3 content3 = new ContentFragment3();
            fragmentTransaction.replace(R.id.content, content3);
            fragmentTransaction.commit();
            break;
        default:
            break;
        }
    }

    // @Override
    // public void onArticleSelected(int position) {
    // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    // switch (position) {
    // case 0:
    // ContentFragment1 content1 = new ContentFragment1();
    // fragmentTransaction.replace(R.id.content, content1);
    // fragmentTransaction.commit();
    // break;
    // case 1:
    // ContentFragment2 content2 = new ContentFragment2();
    // fragmentTransaction.replace(R.id.content, content2);
    // fragmentTransaction.commit();
    // break;
    //
    // default:
    // break;
    // }
    // }

}
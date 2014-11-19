package com.example.achatengine;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main2Activity extends Activity {

    public static String LABLE_TEXT[] = { "", "A", "B", "C", "D" };
    private LinearLayout layoutViewContent;
    private Button btn_single;
    private Button btn_total;
    private List<String> options = new ArrayList<String>();
    private BudgetPieChart view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
        view = new BudgetPieChart();
        layoutViewContent.setBackgroundColor(0xffffffff);
        layoutViewContent.addView(view.getBudgetPieChat(Main2Activity.this), new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

}
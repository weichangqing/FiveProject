package com.example.achatengine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class Main6Activity extends Activity implements OnClickListener {

    private LinearLayout layoutViewContent;
    private BudgetDoughnutChart view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
        // view = new BarChartView(MainActivity.this, isSingleView);
        view = new BudgetDoughnutChart();
        // view.initData(first, second, options, "双柱状图");
        layoutViewContent.setBackgroundColor(0xffffffff);
        layoutViewContent.addView(view.getDoughnutChartView(Main6Activity.this));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

    }

}
package com.example.achatengine;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Main3Activity extends Activity implements OnClickListener {

    public static String LABLE_TEXT[] = { "", "A", "B", "C", "D" };
    private LinearLayout layoutViewContent;
    private Button btn_single;
    private Button btn_total;
    private double first[] = new double[] { 30, 40, 60, 80 };
    private double second[] = new double[] { 50, 60, 88, 91 };
    private List<String> options = new ArrayList<String>();
    private boolean isSingleView;
    private SensorValuesChart view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSingleView = false;
        layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
        // view = new BarChartView(MainActivity.this, isSingleView);
        view = new SensorValuesChart();
        // view.initData(first, second, options, "双柱状图");
        layoutViewContent.setBackgroundColor(0xffffffff);
        layoutViewContent.addView(view.getSenseView(Main3Activity.this));
        btn_single = (Button) findViewById(R.id.single_view_btn);
        btn_total = (Button) findViewById(R.id.double_view_btn);
        btn_single.setOnClickListener(this);
        btn_total.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        // switch (id) {
        // case R.id.single_view_btn:
        // isSingleView = true;
        // layoutViewContent.removeAllViews();
        // view = new BarChartView(MainActivity.this, isSingleView);
        // view.initData(first, second, options, "单柱状图");
        // layoutViewContent.addView(view.getBarChartView());
        // break;
        // case R.id.double_view_btn:
        // isSingleView = false;
        // layoutViewContent.removeAllViews();
        // view = new BarChartView(MainActivity.this, isSingleView);
        // view.initData(first, second, options, "双柱状图");
        // layoutViewContent.addView(view.getBarChartView());
        //
        // break;
        // }

    }

}
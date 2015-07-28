package com.example.achatengine;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

    public static String LABLE_TEXT[] = { "", "E", "A", "B", "C", "D", "W", "G", "H", "AI" };
    private LinearLayout layoutViewContent;
    private Button btn_single;
    private Button btn_total;
    private Button btn_round;
    private Button btn_line;
    private Button btn_line_new;
    private double first[] = new double[] { 55, 36, 25, 20, 9, 31, 4, 17, 21 };
    private double second[] = new double[] { 50, 60, 88, 91 };
    private List<String> options = new ArrayList<String>();
    private boolean isSingleView;
    private BarChartView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isSingleView = true;
        for (String tem : LABLE_TEXT) {
            options.add(tem);
        }

        layoutViewContent = (LinearLayout) findViewById(R.id.barview_content);
        view = new BarChartView(MainActivity.this, isSingleView);
        view.initData(first, second, options, "单柱形");
        System.out.print("==============");
        layoutViewContent.setBackgroundColor(0xffffffff);
        layoutViewContent.addView(view.getBarChartView());
        btn_single = (Button) findViewById(R.id.single_view_btn);
        btn_total = (Button) findViewById(R.id.double_view_btn);
        btn_round = (Button) findViewById(R.id.round_view_btn);
        btn_line = (Button) findViewById(R.id.line_view_btn);
        btn_line_new = (Button) findViewById(R.id.line_new_btn);
        btn_single.setOnClickListener(this);
        btn_total.setOnClickListener(this);
        btn_round.setOnClickListener(this);
        btn_line.setOnClickListener(this);
        btn_line_new.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
        case R.id.single_view_btn:
            isSingleView = true;
            layoutViewContent.removeAllViews();
            view = new BarChartView(MainActivity.this, isSingleView);
            view.initData(first, second, options, "fffffffffffffffff");
            layoutViewContent.addView(view.getBarChartView());
            break;
        case R.id.double_view_btn:
            // isSingleView = false;
            // layoutViewContent.removeAllViews();
            // view = new BarChartView(MainActivity.this, isSingleView);
            // view.initData(first, second, options, "双柱状图");
            // layoutViewContent.addView(view.getBarChartView());
            Intent intentLineAverge = new Intent(this, Main4Activity.class);
            startActivity(intentLineAverge);
            break;
        case R.id.round_view_btn:
            Intent intentround = new Intent(this, Main2Activity.class);
            startActivity(intentround);
            break;
        case R.id.line_view_btn:
            Intent intentLine = new Intent(this, Main3Activity.class);
            startActivity(intentLine);
            break;
        case R.id.line_new_btn:
            Intent intentLine5 = new Intent(this, Main5Activity.class);
            startActivity(intentLine5);
            break;
        }

    }

}

package com.example.achatengine;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

public class BarChartView {

    private static int margins[] = new int[] { 50, 50, 50, 50 };
    private static String[] titles = new String[] { "", "", "", "", "", "", "", "", "" };
    private List<double[]> values = new ArrayList<double[]>();
    private static int[] colors = new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.GRAY, Color.YELLOW,
            Color.WHITE, Color.LTGRAY, Color.BLACK };
    private XYMultipleSeriesRenderer renderer;
    private Context mContext;
    private String mTitle;
    private List<String> option;
    private boolean isSingleView = false;

    public BarChartView(Context context, boolean isSingleView) {
        this.mContext = context;
        this.isSingleView = isSingleView;
        this.renderer = new XYMultipleSeriesRenderer();

    }

    public void initData(double[] firstAnswerPercent, double[] lastAnswerPercent, List<String> option, String title) {
        this.values.add(firstAnswerPercent);
        if (!isSingleView) {
            this.values.add(lastAnswerPercent);
        }
        this.mTitle = title;
        this.option = option;

    }

    public View getBarChartView() {
        buildBarRenderer();
        setChartSettings(renderer, mTitle, "", "dfafdafdadfa", 0, 10, 0, 80, Color.BLACK, Color.LTGRAY);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        if (!isSingleView) {
            renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        }
        int size = option.size();
        for (int i = 0; i < size; i++) {
            renderer.addXTextLabel(i, option.get(i));
        }
        renderer.setMargins(margins);
        renderer.setMarginsColor(0x00ffffff);
        renderer.setPanEnabled(false, false);
        renderer.setZoomEnabled(false, false);// 设置x，y方向都不可以放大或缩�?
        renderer.setZoomRate(1.0f);
        renderer.setInScroll(false);
        renderer.setBackgroundColor(0x00ffffff);
        renderer.setApplyBackgroundColor(false);
        View view = ChartFactory.getBarChartView(mContext, buildBarDataset(titles, values), renderer, Type.DEFAULT); // Type.STACKED
        view.setBackgroundColor(0x00ffffff);
        return view;
    }

    private XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(0);
            series.add(v[i]);
            // int seriesLength = v.length;
            // for (int k = 0; k < seriesLength; k++) {
            // series.add(v[k]);
            // }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle, String yTitle,
            double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setXLabels(0);
        renderer.setYLabels(10);
        renderer.setLabelsTextSize(25);
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setXLabelsAlign(Align.LEFT);
        // renderer.setXLabelsColor(0xff000000);//设置X轴上的字体颜�?
        // renderer.setYLabelsColor(0,0xff000000);//设置Y轴上的字体颜�?

    }

    /*
     * 初始化柱子风�?
     */
    protected void buildBarRenderer() {
        if (null == renderer) {
            return;
        }
        renderer.setBarWidth(23);
        renderer.setBarSpacing(20);
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        int length = isSingleView ? colors.length : colors.length;
        for (int i = 0; i < length; i++) {
            SimpleSeriesRenderer ssr = new SimpleSeriesRenderer();
            ssr.setChartValuesTextAlign(Align.RIGHT);
            ssr.setChartValuesTextSize(15);
            ssr.setDisplayChartValues(true);
            ssr.setColor(colors[i]);
            renderer.addSeriesRenderer(ssr);
        }
    }
}

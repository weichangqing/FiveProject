/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.achatengine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.MathHelper;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * Temperature sensor demo chart.
 */
public class SensorValuesChart {
    private static final long HOUR = 3600 * 1000;

    private static final long DAY = HOUR * 24;

    private static final int HOURS = 24;

    /**
     * Builds an XY multiple series renderer.
     * 
     * @param colors
     *            the series rendering colors
     * @param styles
     *            the series point styles
     * @return the XY multiple series renderers
     */
    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        setRenderer(renderer, colors, styles);
        return renderer;
    }

    protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors, PointStyle[] styles) {
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPointSize(5f);
        renderer.setMargins(new int[] { 20, 30, 15, 20 });
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            renderer.addSeriesRenderer(r);
        }
    }

    /**
     * Sets a few of the series renderer settings.
     * 
     * @param renderer
     *            the renderer to set the properties to
     * @param title
     *            the chart title
     * @param xTitle
     *            the title for the X axis
     * @param yTitle
     *            the title for the Y axis
     * @param xMin
     *            the minimum value on the X axis
     * @param xMax
     *            the maximum value on the X axis
     * @param yMin
     *            the minimum value on the Y axis
     * @param yMax
     *            the maximum value on the Y axis
     * @param axesColor
     *            the axes color
     * @param labelsColor
     *            the labels color
     */
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
    }

    /**
     * Builds an XY multiple time dataset using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param xValues
     *            the values for the X axis
     * @param yValues
     *            the values for the Y axis
     * @return the XY multiple time dataset
     */
    protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues, List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            TimeSeries series = new TimeSeries(titles[i]);
            Date[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }

    /**
     * Builds a category series using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param values
     *            the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }

        return series;
    }

    /**
     * Builds a bar multiple series dataset using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param values
     *            the values
     * @return the XY multiple bar dataset
     */
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }

    /**
     * Executes the chart demo.
     * 
     * @param context
     *            the context
     * @return the built intent
     */
    public View getSenseView(Context context) {
        String[] titles = new String[] { "Inside", "Outside" };
        long now = Math.round(new Date().getTime() / DAY) * DAY;
        List<Date[]> x = new ArrayList<Date[]>();
        for (int i = 0; i < titles.length; i++) {
            Date[] dates = new Date[HOURS];
            for (int j = 0; j < HOURS; j++) {
                dates[j] = new Date(now - (HOURS - j) * HOUR);
            }
            x.add(dates);
        }
        List<double[]> values = new ArrayList<double[]>();

        values.add(new double[] { 21.2, 21.5, 21.7, 21.5, 21.4, 21.4, 21.3, 21.1, 20.6, 20.3, 20.2, 19.9, 19.7, 19.6,
                19.9, 20.3, 20.6, 20.9, 21.2, 21.6, 21.9, 22.1, 21.7, 21.5 });
        values.add(new double[] { 1.9, 1.2, 0.9, 0.5, 0.1, -0.5, -0.6, MathHelper.NULL_VALUE, MathHelper.NULL_VALUE,
                -1.8, -0.3, 1.4, 3.4, 4.9, 7.0, 6.4, 3.4, 2.0, 1.5, 0.9, -0.5, MathHelper.NULL_VALUE, -1.9, -2.5, -4.3 });

        int[] colors = new int[] { Color.GREEN, Color.BLUE };
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND };
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(renderer, "Sensor temperature", "Hour", "Celsius degrees", x.get(0)[0].getTime(),
                x.get(0)[HOURS - 1].getTime(), -5, 30, Color.LTGRAY, Color.LTGRAY);
        renderer.setXLabels(10);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setPanEnabled(false);
        renderer.setClickEnabled(false);
        renderer.setZoomEnabled(false);
        View view = ChartFactory.getTimeChartView(context, buildDateDataset(titles, x, values), renderer, "h:mm a");
        return view;
    }

}

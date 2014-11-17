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

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * Budget demo pie chart.
 */
public class BudgetPieChart {

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

    protected CategorySeries buildCategoryDatasetTwo(String title, String[] name, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = -1;
        for (double value : values) {
            series.add(name[+ ++k], value);
        }
        return series;
    }

    /**
     * Builds a category renderer to use the provided colors.
     * 
     * @param colors
     *            the colors
     * @return the category renderer
     */
    protected XYMultipleSeriesRenderer buildCategoryRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setLabelsTextSize(35);
        renderer.setLegendTextSize(35);
        renderer.setMargins(new int[] { 20, 30, 15, 20 });
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Executes the chart demo.
     * 
     * @param context
     *            the context
     * @return the built intent
     */
    public View getBudgetPieChat(Context context) {
        double[] values = new double[] { 40, 20, 20, 10, 10, };
        String[] strValues = { "差", "一般", "很好", "良", "优秀" };
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };
        XYMultipleSeriesRenderer renderer = buildCategoryRenderer(colors);
        // renderer.setZoomButtonsVisible(true);
        renderer.setPanEnabled(false);
        renderer.setClickEnabled(false);
        renderer.setPanEnabled(false, false);
        renderer.setZoomEnabled(false, false);
        renderer.setChartTitleTextSize(30);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(false);
        renderer.setExternalZoomEnabled(false);
        renderer.setOrientation(Orientation.HORIZONTAL);
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.CENTER);

        // SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        // r.setGradientEnabled(true);
        // r.setGradientStart(0, Color.BLUE);
        // r.setGradientStop(0, Color.GREEN);
        // r.setHighlighted(true);
        // View view = ChartFactory.getPieChartView(context, buildCategoryDataset("ddd", values), renderer);
        View view = ChartFactory.getPieChartView(context, buildCategoryDatasetTwo("ddd", strValues, values), renderer);
        // // Type.STACKED
        // View view = ChartFactory.getBarChartView(mContext, buildBarDataset(null, null), renderer, Type.DEFAULT); //
        // Type.STACKED
        view.setBackgroundColor(0x00ffffff);
        return view;
    }
}

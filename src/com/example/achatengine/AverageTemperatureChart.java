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
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.View;

/**
 * Average temperature demo chart.
 */
public class AverageTemperatureChart {

    public View getAverageView(Context context) {
        String[] titles = new String[] { "Crete", "Corfu", "Thassos", "Skiathos" };
        List<double[]> x = new ArrayList<double[]>();
        for (int i = 0; i < titles.length; i++) {
            x.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
        }
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12.3, 12.5, 13.8, 16.8, 20.4, 24.4, 26.4, 26.1, 23.6, 20.3, 17.2, 13.9 });
        values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14, 11 });
        values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 15, 9, 6 });
        values.add(new double[] { 9, 10, 11, 15, 19, 23, 26, 25, 22, 18, 13, 10 });
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.CYAN, Color.YELLOW };
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.TRIANGLE,
                PointStyle.SQUARE };
        XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
        }
        setChartSettings(renderer, "Average temperature", "Month", "Temperature", 0.5, 12.5, -10, 40, Color.LTGRAY,
                Color.LTGRAY);
        renderer.setXLabels(12);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.RIGHT);
        renderer.setZoomButtonsVisible(true);
        renderer.setPanLimits(new double[] { -10, 20, -10, 40 });
        renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });
        renderer.setPanEnabled(false);
        renderer.setClickEnabled(false);
        renderer.setZoomEnabled(false);

        XYMultipleSeriesDataset dataset = buildDataset(titles, x, values);
        XYSeries series = dataset.getSeriesAt(0);
        series.addAnnotation("Vacation", 6, 30);
        View view = ChartFactory.getLineChartView(context, dataset, renderer);
        // Intent intent = ChartFactory.getLineChartIntent(context, dataset, renderer, "Average temperature");
        return view;
    }

    /**
     * Builds an XY multiple dataset using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param xValues
     *            the values for the X axis
     * @param yValues
     *            the values for the Y axis
     * @return the XY multiple dataset
     */
    protected XYMultipleSeriesDataset buildDataset(String[] titles, List<double[]> xValues, List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        addXYSeries(dataset, titles, xValues, yValues, 0);
        return dataset;
    }

    public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles, List<double[]> xValues,
            List<double[]> yValues, int scale) {
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            XYSeries series = new XYSeries(titles[i], scale);
            double[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
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

}

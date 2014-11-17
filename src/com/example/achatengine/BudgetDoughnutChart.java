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
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

/**
 * Budget demo pie chart.
 */
public class BudgetDoughnutChart {
    /**
     * Builds a category renderer to use the provided colors.
     * 
     * @param colors
     *            the colors
     * @return the category renderer
     */
    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] { 20, 30, 15, 0 });
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a multiple category series using the provided values.
     * 
     * @param titles
     *            the series titles
     * @param values
     *            the values
     * @return the category series
     */
    protected MultipleCategorySeries buildMultipleCategoryDataset(String title, List<String[]> titles,
            List<double[]> values) {
        MultipleCategorySeries series = new MultipleCategorySeries(title);
        int k = 0;
        for (double[] value : values) {
            series.add(2007 + k + "", titles.get(k), value);
            k++;
        }
        return series;
    }

    /**
     * Executes the chart demo.
     * 
     * @param context
     *            the context
     * @return the built intent
     */
    public View getDoughnutChartView(Context context) {
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 12, 14, 11, 10, 19 });
        // values.add(new double[] { 10, 9, 14, 20, 11 });
        List<String[]> titles = new ArrayList<String[]>();
        titles.add(new String[] { "P1", "P2", "P3", "P4", "P5" });
        // titles.add(new String[] { "Project1", "Project2", "Project3", "Project4", "Project5" });
        int[] colors = new int[] { Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN };

        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.rgb(222, 222, 200));
        renderer.setLabelsColor(Color.GRAY);
        return ChartFactory.getDoughnutChartView(context,
                buildMultipleCategoryDataset("Project budget", titles, values), renderer);
        /*
         * return ChartFactory.getDoughnutChartIntent(context, buildMultipleCategoryDataset("Project budget", titles,
         * values), renderer, "Doughnut chart demo");
         */
    }

}

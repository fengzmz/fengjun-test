package com.test.easy_poi.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.io.File;

public class JFreeChartToFileUtil {

    public static void createPieChart(DefaultPieDataset pds, File file,String title) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createPieChart(title, pds, false, false, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            // 得到图块,准备设置标签的字体
            PiePlot plot = (PiePlot) chart.getPlot();
            // 设置标签字体
            plot.setLabelFont(font);
            plot.setStartAngle(3.14f / 2f);
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置标签生成器(默认{0})
            // {0}:key {1}:value {2}:百分比 {3}:sum
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}占{2}"));
            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(file, chart, 125, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createBarChart(CategoryDataset pds, File file,String title) {
        try {
            // 分别是:显示图表的标题、需要提供对应图表的DateSet对象、是否显示图例、是否生成贴士以及是否生成URL链接
            JFreeChart chart = ChartFactory.createBarChart3D(title, null,
                    null, pds, PlotOrientation.VERTICAL,
                    true, true, true);
            // 如果不使用Font,中文将显示不出来
            Font font = new Font("宋体", Font.BOLD, 12);
            // 设置图片标题的字体
            chart.getTitle().setFont(font);
            chart.getLegend().setItemFont(font);
            // 得到图块,准备设置标签的字体
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            // 设置plot的前景色透明度
            plot.setForegroundAlpha(0.7f);
            // 设置plot的背景色透明度
            plot.setBackgroundAlpha(0.0f);
            // 设置标签生成器(默认{0})

            ValueAxis rangeAxis = plot.getRangeAxis();
            CategoryAxis domainAxis = plot.getDomainAxis();

            rangeAxis.setLabelFont(font);
            rangeAxis.setTickLabelFont(font);
            domainAxis.setLabelFont(font);
            domainAxis.setTickLabelFont(font);
            domainAxis.setMaximumCategoryLabelLines(10);
            domainAxis.setMaximumCategoryLabelWidthRatio(0.5f);

            // 将内存中的图片写到本地硬盘
            ChartUtilities.saveChartAsJPEG(file, chart, 600, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


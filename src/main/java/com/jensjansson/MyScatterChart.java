package com.jensjansson;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.*;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by Jens Jansson on 17.09.2015.
 */
public class MyScatterChart extends VerticalLayout {

    ChartExtension extension;
    Chart chart;
    List<ZoomCoordinates> list = new ArrayList<ZoomCoordinates>();
    Button goBackInZooming;
    Label zoominfo = new Label("zoom info");

    public MyScatterChart(int points){
        HorizontalLayout buttonBar = createButtonBar();
        chart = createChart(points);
        extension = new ChartExtension(this, chart);
        addComponents(buttonBar, chart);
        setSpacing(true);
        writeOutZoomInfo();
    }

    private HorizontalLayout createButtonBar(){
        final HorizontalLayout buttonBar = new HorizontalLayout();
        Button drawBadge = new Button("Draw badge", new Button.ClickListener(){
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                extension.addBadge();

            }
        });

        final Button enableZoomTracking = new Button("Enable zoom tracking");
        enableZoomTracking.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                extension.enableZoomTracking();
                buttonBar.replaceComponent(enableZoomTracking, goBackInZooming);
            }
        });

        goBackInZooming = new Button("Go back in zoom state", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if(list.size()>1) {
                    ZoomCoordinates zoom = list.get(list.size() - 2);
                    zoomTo(zoom);
                    list.remove(list.size() - 1);
                } else {
                    list.clear();
                    XAxis xAxis = chart.getConfiguration().getxAxis();
                    YAxis yAxis = chart.getConfiguration().getyAxis();
                    xAxis.setExtremes(148, 182);
                    yAxis.setExtremes(0, 125);
                    goBackInZooming.setEnabled(false);
                }
                writeOutZoomInfo();
            }
        });
        goBackInZooming.setEnabled(false);
        buttonBar.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        buttonBar.addComponents(drawBadge, enableZoomTracking, zoominfo);
        buttonBar.setSpacing(true);
        return buttonBar;
    }

    private void writeOutZoomInfo(){
        String string = "Zoom states: ";
        for(ZoomCoordinates zoom : list){
            string = string + "[" + zoom.xMin + "-" + zoom.xMax + "] ";
        }
        zoominfo.setValue(string);
    }

    private void zoomTo(ZoomCoordinates zoom){
        XAxis xAxis = chart.getConfiguration().getxAxis();
        YAxis yAxis = chart.getConfiguration().getyAxis();
        xAxis.setExtremes(zoom.xMin, zoom.xMax);
        yAxis.setExtremes(zoom.yMin, zoom.yMax);
        System.out.println("Setting zooms: " + zoom.xMin + " - " + zoom.xMax);

    }

    private Chart createChart(int points) {
        Chart chart = new Chart(ChartType.SCATTER);
        Configuration conf = chart.getConfiguration();

        conf.getChart().setZoomType(ZoomType.XY);
        conf.disableCredits();
        conf.setTitle("Height vs Weight");
        conf.setSubTitle("Polygon series in Vaadin Charts.");

        Tooltip tooltip = conf.getTooltip();
        tooltip.setHeaderFormat("{series.name}");
        tooltip.setPointFormat("{point.x} cm, {point.y} kg");

        XAxis xAxis = conf.getxAxis();
        xAxis.setStartOnTick(true);
        xAxis.setEndOnTick(true);
        xAxis.setShowLastLabel(true);
        xAxis.setTitle("Height (cm)");

        YAxis yAxis = conf.getyAxis();
        yAxis.setTitle("Weight (kg)");

        AbstractLinePlotOptions plotOptions = new PlotOptionsScatter();
        plotOptions.setThreshold(0);
        DataSeries scatter = new DataSeries();
        scatter.setPlotOptions(plotOptions);
        scatter.setName("Observations");
        fillScatter(scatter, points);
        conf.addSeries(scatter);
        return chart;
    }

    private void fillScatter(DataSeries series, int points) {
        Random random = new Random();
        for (int i = 0; i < points; i++) {
            double x = random.nextDouble() * 30 + 150;
            double y = 60;
            if (random.nextBoolean()) {
                y += random.nextDouble() * 15;
                if (random.nextBoolean() && x > 170) {
                    y += random.nextDouble() * 30;
                }
            } else {
                y -= random.nextDouble() * 20;
            }
            x = Math.floor(x*10)/10;
            y = Math.floor(y*10)/10;

            int colorNumber = (int) Math.floor((x-150)/30*255);

            Color color = new SolidColor(colorNumber,0,255-colorNumber);
            DataSeriesItem item = new DataSeriesItem(x, y);
            // item.setColor(color);
            series.add(item);
        }
    }

    public void addZoomEvent(ZoomCoordinates zoom){
        list.add(zoom);
        goBackInZooming.setEnabled(true);
        writeOutZoomInfo();
    }
}

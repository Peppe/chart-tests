package com.jensjansson;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@Widgetset("com.jensjansson.MyAppWidgetset")
public class MyUI extends UI {

    MyScatterChart newestChart;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(false);
        setContent(layout);

        final VerticalLayout chartLayout = new VerticalLayout();
        chartLayout.setSpacing(true);
        chartLayout.setMargin(true);

        final TextField amountOfPoints = new TextField("Amount of points", "1000");
        Button button = new Button("Add a scatter graph");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {

                int points = Integer.parseInt(amountOfPoints.getValue());
                newestChart = new MyScatterChart(points);
                chartLayout.addComponentAsFirst(newestChart);
            }
        });


        HorizontalLayout hl = new HorizontalLayout(amountOfPoints, button);
        hl.setComponentAlignment(amountOfPoints, Alignment.BOTTOM_LEFT);
        hl.setComponentAlignment(button,Alignment.BOTTOM_LEFT);
        hl.setExpandRatio(button,1);
        hl.setSpacing(true);
        hl.setMargin(true);
        hl.setWidth("100%");
        hl.addStyleName("dark");

        layout.addComponent(hl);
        layout.addComponent(chartLayout);
        layout.setSpacing(true);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

package com.jensjansson;

import com.vaadin.addon.charts.Chart;
import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.shared.communication.ServerRpc;

/**
 * Created by Jens Jansson on 17.09.2015.
 */
@JavaScript({"chartextension.js"})
public class ChartExtension extends AbstractJavaScriptExtension {

    public ChartExtension(final MyScatterChart wrapper, Chart chart){
        super(chart);
        registerRpc(new ZoomEvent() {
            @Override
            public void onZoomEvent(int xMin, int xMax, int yMin, int yMax) {
                System.out.println("zoomy " + xMin);
                wrapper.addZoomEvent(new ZoomCoordinates(xMin, xMax, yMin, yMax));
            }
        });
    }

    public void addBadge() {
        callFunction("addBadge");
    }

    public void enableZoomTracking() {
        callFunction("enableZoomTracking");
    }

    public interface ZoomEvent extends ServerRpc {
        public void onZoomEvent(int xMin, int xMax, int yMin, int yMax);
    }
}

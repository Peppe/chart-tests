package com.jensjansson;

/**
 * Created by Jens Jansson on 17.09.2015.
 */
public class ZoomCoordinates {
    public final int xMin;
    public final int xMax;
    public final int yMin;
    public final int yMax;

    public ZoomCoordinates(int xMin, int xMax, int yMin, int yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }
}

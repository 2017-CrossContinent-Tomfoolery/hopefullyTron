package tronplease;

import java.awt.*;

/**
 * Created by Benjamin on 7/5/2017.
 */
public class BikeAndLocation {

    private TronBike tronBike;
    private Point    projectedLocation;

    public BikeAndLocation(TronBike tronBike, Point projectedLocation) {
        this.setTronBike(tronBike); this.setProjectedLocation(projectedLocation);
    }

    public TronBike getTronBike() {
        return tronBike;
    }

    public void setTronBike(TronBike tronBike) {
        this.tronBike = tronBike;
    }

    public Point getProjectedLocation() {
        return projectedLocation;
    }

    public void setProjectedLocation(Point projectedLocation) {
        this.projectedLocation = projectedLocation;
    }
}

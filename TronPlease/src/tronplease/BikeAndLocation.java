package tronplease;

import java.awt.*;

/**
 * Created by Benjamin on 7/5/2017.
 */
public class BikeAndLocation {

    private TronBike tronBike;
    private Point    position;

    public BikeAndLocation(TronBike tronBike, Point position) {
        this.setTronBike(tronBike);
        this.setProjectedLocation(position);
    }

    public TronBike getTronBike() {
        return tronBike;
    }

    public void setTronBike(TronBike tronBike) {
        this.tronBike = tronBike;
    }

    public Point getProjectedLocation() {
        return position;
    }

    public void setProjectedLocation(Point projectedLocation) {
        this.position = projectedLocation;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronplease;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Benjamin
 */
public class TronBike {

    public TronBike() {
    }

    public TronBike(Point location, Direction direction, GridDrawData drawData, BikeProjectedLocationValidatorIntf projectedLocationValidator) {
        this.location = location;
        this.direction = direction;
        this.drawData = drawData;
        this.projectedLocationValidator = projectedLocationValidator;
    }

    private Point location;
    private Direction direction;
    private GridDrawData drawData;
    private BikeProjectedLocationValidatorIntf projectedLocationValidator;

    public void drawBike(Graphics graphics) {
        Point anchor = drawData.getCellSystemCoordinate(location);
        graphics.fillOval(anchor.x, anchor.y, drawData.getCellDimension(), drawData.getCellDimension());
    }

    public void move() {
        Point projectedLocation = getLocation();
        switch (direction) {
            case UP:
                projectedLocation = new Point(location.x, location.y - 1);
                break;
            case DOWN:
                projectedLocation = new Point(location.x, location.y + 1);
                break;
            case LEFT:
                projectedLocation = new Point(location.x - 1, location.y);
                break;
            case RIGHT:
                projectedLocation = new Point(location.x + 1, location.y);
                break;
        }

        projectedLocationValidator.validateLocation(new BikeAndLocation(this, projectedLocation));
    }

    public void moveLeft() {
        if (location.x - 1 >= 0) {
            location = new Point(location.x - 1, location.y);
        }
    }

    public void moveRight() {
        if (location.x + 1 < drawData.getColumns()) {
            location = new Point(location.x + 1, location.y);
        }
    }

    public void moveUp() {
        if (location.y - 1 >= 0) {
            location = new Point(location.x, location.y - 1);
        }
    }

    public void moveDown() {
        if (location.y + 1 < drawData.getRows()) {
            location = new Point(location.x, location.y + 1);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the drawData
     */
    public GridDrawData getDrawData() {
        return drawData;
    }

    /**
     * @param drawData the drawData to set
     */
    public void setDrawData(GridDrawData drawData) {
        this.drawData = drawData;
    }

    public BikeProjectedLocationValidatorIntf getProjectedLocationValidator() {
        return projectedLocationValidator;
    }

    public void setProjectedLocationValidator(BikeProjectedLocationValidatorIntf projectedLocationValidator) {
        this.projectedLocationValidator = projectedLocationValidator;
    }
//</editor-fold>

}

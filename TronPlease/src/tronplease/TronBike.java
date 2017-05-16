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

    public TronBike(Point location, Direction direction, GridDrawData drawData) {
        this.location = location;
        this.direction = direction;
        this.drawData = drawData;
    }

    private Point location;
    private Direction direction;
    private GridDrawData drawData;

    public void drawBike(Graphics graphics) {
        Point anchor = drawData.getCellSystemCoordinate(location);
        graphics.fillOval(anchor.x, anchor.y, drawData.getCellDimension(), drawData.getCellDimension());
    }

    public void move() {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
        }
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
//</editor-fold>

}

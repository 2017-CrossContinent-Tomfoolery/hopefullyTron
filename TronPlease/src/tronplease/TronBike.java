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

    public TronBike(Point location, Direction direction, boolean playable, GridDrawData drawData, BikeLocationValidatorIntf locationValidator, AIBikeMovementIntf computerMovement) {
        setLocation(location);
        setDirection(direction);
        setPlayable(playable);
        setDrawData(drawData);
        setLocationValidator(locationValidator);
        setComputerMovement(computerMovement);
    }

    private Point                     location;
    private Direction                 direction;
    private boolean                   playable;
    private GridDrawData              drawData;
    private BikeLocationValidatorIntf locationValidator;
    private AIBikeMovementIntf        computerMovement;

    public void drawBike(Graphics graphics) {
        Point anchor = drawData.getCellSystemCoordinate(location);
        graphics.fillRect(anchor.x, anchor.y, drawData.getCellDimension(), drawData.getCellDimension());
    }

    public void move() {
        Point projectedLocation = giveCoordinates(direction);
        locationValidator.validateLocation(new BikeAndLocation(this, projectedLocation));
    }

    public Point giveCoordinates(Direction direction) {
        Point coordinates = null;
        switch (direction) {
            case UP:
                coordinates = new Point(location.x, location.y - 1);
                break;
            case DOWN:
                coordinates = new Point(location.x, location.y + 1);
                break;
            case LEFT:
                coordinates = new Point(location.x - 1, location.y);
                break;
            case RIGHT:
                coordinates = new Point(location.x + 1, location.y);
                break;
        }
        return coordinates;
    }


    public void computerMove() {
        Point projectedLocation = getLocation();
        projectedLocation = computerMovement.computerMove(this);
        locationValidator.validateLocation(new BikeAndLocation(this, projectedLocation));
    }


//    public Point left() {
//        return new Point(location.x - 1, location.y);
//    }
//
//    public Point right() {
//        return new Point(location.x + 1, location.y);
//    }
//
//    public Point above() {
//        return new Point(location.x, location.y - 1);
//    }
//
//    public Point below() {
//        return new Point(location.x, location.y + 1);
//    }

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

    public BikeLocationValidatorIntf getLocationValidator() {
        return locationValidator;
    }

    public void setLocationValidator(BikeLocationValidatorIntf locationValidator) {
        this.locationValidator = locationValidator;
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public AIBikeMovementIntf getComputerMovement() {
        return computerMovement;
    }

    public void setComputerMovement(AIBikeMovementIntf computerMovement) {
        this.computerMovement = computerMovement;
    }


//</editor-fold>

}

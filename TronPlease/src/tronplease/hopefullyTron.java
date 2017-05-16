/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronplease;

import environment.Environment;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Benjamin
 */
class hopefullyTron extends Environment implements GridDrawData {

    //<editor-fold defaultstate="collapsed" desc="Constants">
    private static final int GRID_ROWS = 100;
    private static final int GRID_COLS = 150;
    private static final int GRID_DIMENSION = 6;
    private static final Point GRID_ANCHOR = new Point(20, 20);
    private static final Point PLAYER_STARTING_LOCATION = new Point(20, 20);
    private static final int MOVE_DELAY_TIME = 2;
//</editor-fold>

    public hopefullyTron() {
    }

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(GRID_COLS, GRID_ROWS, GRID_DIMENSION, GRID_ANCHOR, Color.GRAY);
        playerBike = new TronBike(PLAYER_STARTING_LOCATION, Direction.UP, this);
    }

    @Override
    public void timerTaskHandler() {
        if (playerBike != null) {
            if (timerDelay == MOVE_DELAY_TIME) {
                playerBike.move();
                timerDelay = 0;
            }
            timerDelay++;
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (playerBike != null) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                playerBike.setDirection(Direction.LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                playerBike.setDirection(Direction.RIGHT);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                playerBike.setDirection(Direction.DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                playerBike.setDirection(Direction.UP);
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.drawGrid(graphics);
//            graphics.fillOval(grid.getCellSystemCoordinates(3, 3).x, grid.getCellSystemCoordinates(3, 3).y, grid.getDimension(), grid.getDimension());
        }
        graphics.setColor(Color.RED);
        if (playerBike != null) {
            playerBike.drawBike(graphics);
        }
    }
//</editor-fold>

    private Grid grid;
    private TronBike playerBike;

    private int timerDelay = 0;

    //<editor-fold defaultstate="collapsed" desc="GridDrawData Abstract Methods">
    @Override
    public int getCellDimension() {
        return grid.getDimension();
    }

    @Override
    public int getColumns() {
        return grid.getColumns();
    }

    @Override
    public int getRows() {
        return grid.getRows();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellCoordinate) {
        return grid.getCellSystemCoordinates(cellCoordinate);
    }
//</editor-fold>

}

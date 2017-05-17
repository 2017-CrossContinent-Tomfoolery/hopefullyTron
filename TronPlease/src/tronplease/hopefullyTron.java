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
    private static final int GRID_DIMENSION = 5;
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
        
        tronArena = new int[GRID_COLS][GRID_ROWS];
        for (int col = 0; col < GRID_COLS; col++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                tronArena[col][row] = 0;
            }
        }
    }

    @Override
    public void timerTaskHandler() {
        if (playerBike != null) {
            if (timerDelay == MOVE_DELAY_TIME) {
                playerBike.move();
                tronArena[playerBike.getLocation().x][playerBike.getLocation().y] = 1;
                timerDelay = 0;
            }
            timerDelay++;
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (playerBike != null) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (playerBike.getDirection() != Direction.RIGHT) {
                    playerBike.setDirection(Direction.LEFT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (playerBike.getDirection() != Direction.LEFT) {
                    playerBike.setDirection(Direction.RIGHT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (playerBike.getDirection() != Direction.UP) {
                    playerBike.setDirection(Direction.DOWN);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (playerBike.getDirection() != Direction.DOWN) {
                    playerBike.setDirection(Direction.UP);
                }
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
            grid.drawBorder(graphics);
//            graphics.fillOval(grid.getCellSystemCoordinates(3, 3).x, grid.getCellSystemCoordinates(3, 3).y, grid.getDimension(), grid.getDimension());
        }
        if (tronArena != null) {
            drawTronArena(graphics);
        }
        graphics.setColor(Color.RED);
        if (playerBike != null) {
            playerBike.drawBike(graphics);
        }
    }
//</editor-fold>
    
    public void drawTronArena(Graphics graphics) {
        for (int col = 0; col < tronArena.length; col++) {
            for (int row = 0; row < tronArena[row].length; row++) {
                switch (tronArena[col][row]) {
                    case 0:
                        break;
                    case 1:
                        graphics.setColor(new Color(255, 150, 150));
                        graphics.fillRect(GRID_ANCHOR.x + GRID_DIMENSION * col, GRID_ANCHOR.y + GRID_DIMENSION * row, 
                        GRID_DIMENSION, GRID_DIMENSION);
                        break;
                }
            }
        }
    }

    private Grid grid;
    private TronBike playerBike;

    private int timerDelay = 0;
    private int[][] tronArena;

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

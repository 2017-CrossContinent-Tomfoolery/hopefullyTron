/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronplease;

import environment.Environment;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Benjamin
 */
class HopefullyTron extends Environment implements GridDrawData, BikeProjectedLocationValidatorIntf {

    //<editor-fold defaultstate="collapsed" desc="Constants">
    private static final int GRID_ROWS = 100;
    private static final int GRID_COLS = 150;
    private static final int GRID_DIMENSION = 5;
    private static final Point GRID_ANCHOR = new Point(20, 20);

    private static final Point PLAYER_STARTING_LOCATION = new Point(20, 20);

    private static final int MOVE_DELAY_TIME = 2;
    private static final int SHIMMER_CONTROL_MAX_VALUE = 30;
    private static final int SHIMMER_CONTROL_INTERVAL = 3;
//</editor-fold>

    public HopefullyTron() {
    }

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(GRID_COLS, GRID_ROWS, GRID_DIMENSION, GRID_ANCHOR, Color.GRAY);
        playerBike = new TronBike(PLAYER_STARTING_LOCATION, Direction.UP, this, this);
        
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
                shimmerControl += SHIMMER_CONTROL_INTERVAL;
            }
            if (shimmerControl == SHIMMER_CONTROL_MAX_VALUE) {
                shimmerControl = -SHIMMER_CONTROL_MAX_VALUE;
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
        //<editor-fold defaultstate="collapsed" desc="Antialias">
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//</editor-fold>
        graphics.setColor(new Color(50, 50, 50));
        graphics.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        if (grid != null) {
            grid.drawBorder(graphics);
//            graphics.fillOval(grid.getCellSystemCoordinates(3, 3).x, grid.getCellSystemCoordinates(3, 3).y, grid.getDimension(), grid.getDimension());
        }
        if (tronArena != null) {
            drawTronArena(graphics);
        }
        graphics.setColor(Color.red);
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
                        graphics.setColor(new Color(200 + Math.abs(shimmerControl), 100 + Math.abs(shimmerControl), 100 + Math.abs(shimmerControl)));
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
    private int shimmerControl = -SHIMMER_CONTROL_MAX_VALUE;

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

    //<editor-fold desc="BikeProjectedLocationValidatorIntf Abstract Methods">
    @Override
    public BikeAndLocation validateLocation(BikeAndLocation data) {
        if  (data.getProjectedLocation().x >= 0 && data.getProjectedLocation().x <= GRID_COLS && data.getProjectedLocation().y >= 0 && data.getProjectedLocation().y <= GRID_ROWS ) {
            data.getTronBike().setLocation(data.getProjectedLocation());
        }


        return data;
    }
    //</editor-fold>

    public int getScreenWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public int getScreenHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }


}

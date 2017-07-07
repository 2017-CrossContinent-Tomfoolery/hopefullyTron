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
class HopefullyTron extends Environment implements GridDrawData, BikeLocationValidatorIntf, AIBikeMovementIntf {

    //<editor-fold defaultstate="collapsed" desc="Constants">
    private static final Color BACKGROUND_COLOR = new Color(50, 50, 50);

    private static final int GRID_ROWS = 100;
    private static final int GRID_COLS = 150;
    private static final int GRID_DIMENSION = 5;
    private static final Point GRID_ANCHOR = new Point(20, 20);

    private static final int     STARTING_DISTANCE_FROM_BORDER = 20;
    private static final boolean PLAYER                        = true;
    private static final boolean COMPUTER                      = false;

    private static final Point PLAYER1_STARTING_LOCATION = new Point(STARTING_DISTANCE_FROM_BORDER, STARTING_DISTANCE_FROM_BORDER);
    private static final Color PLAYER1_BIKE_COLOR = Color.red;

    private static final Point PLAYER2_STARTING_LOCATION = new Point(GRID_COLS - STARTING_DISTANCE_FROM_BORDER, STARTING_DISTANCE_FROM_BORDER);
    private static final Color PLAYER2_BIKE_COLOR        = new Color(34, 150, 50);

    private static final int BIKE1_TRAIL_CODE = 1;
    private static final int BIKE2_TRAIL_CODE = 2;

    private static final int MOVE_DELAY_TIME = 2;

    // Bike trail aesthetic constants
    private static final int SHIMMER_CONTROL_MAX_VALUE = 30;
    private static final int SHIMMER_CONTROL_INTERVAL  = 3;
//</editor-fold>

    public HopefullyTron() {
    }

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    @Override
    public void initializeEnvironment() {
        // Grid initialization
        grid = new Grid(GRID_COLS, GRID_ROWS, GRID_DIMENSION, GRID_ANCHOR, Color.GRAY);

        // Bike1 initialization
        player1Bike = new TronBike(PLAYER1_STARTING_LOCATION, Direction.RIGHT, PLAYER, this, this, this);
        // Bike2 initialization
        player2Bike = new TronBike(PLAYER2_STARTING_LOCATION, Direction.LEFT, COMPUTER, this, this, this);

        // tronArena initialization
        tronArena = new int[GRID_COLS][GRID_ROWS];
        for (int col = 0; col < GRID_COLS; col++) {
            for (int row = 0; row < GRID_ROWS; row++) {
                tronArena[col][row] = 0;
            }
        }
    }

    @Override
    public void timerTaskHandler() {
        if (timerDelay == MOVE_DELAY_TIME) {
            if (player1Bike != null) {
                if (player1Bike.isPlayable()) {
                    player1Bike.move();
                } else {
                    player1Bike.computerMove();
                }
                tronArena[player1Bike.getLocation().x][player1Bike.getLocation().y] = BIKE1_TRAIL_CODE;
            }
            if (player2Bike != null) {
                if (player2Bike.isPlayable()) {
                    player2Bike.move();
                } else {
                    player2Bike.computerMove();
                }
                tronArena[player2Bike.getLocation().x][player2Bike.getLocation().y] = BIKE2_TRAIL_CODE;
            }
            timerDelay = 0;
            shimmerControl += SHIMMER_CONTROL_INTERVAL;
        }
        if (shimmerControl == SHIMMER_CONTROL_MAX_VALUE) {
            shimmerControl = -SHIMMER_CONTROL_MAX_VALUE;
        }
        timerDelay++;
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (player1Bike != null && player1Bike.isPlayable()) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (player1Bike.getDirection() != Direction.RIGHT) {
                    player1Bike.setDirection(Direction.LEFT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (player1Bike.getDirection() != Direction.LEFT) {
                    player1Bike.setDirection(Direction.RIGHT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (player1Bike.getDirection() != Direction.UP) {
                    player1Bike.setDirection(Direction.DOWN);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (player1Bike.getDirection() != Direction.DOWN) {
                    player1Bike.setDirection(Direction.UP);
                }
            }
        }

        if (player2Bike != null && player2Bike.isPlayable()) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (player2Bike.getDirection() != Direction.RIGHT) {
                    player2Bike.setDirection(Direction.LEFT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (player2Bike.getDirection() != Direction.LEFT) {
                    player2Bike.setDirection(Direction.RIGHT);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (player2Bike.getDirection() != Direction.UP) {
                    player2Bike.setDirection(Direction.DOWN);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (player2Bike.getDirection() != Direction.DOWN) {
                    player2Bike.setDirection(Direction.UP);
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
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        if (grid != null) {
            grid.drawBorder(graphics);
//            graphics.fillOval(grid.getCellSystemCoordinates(3, 3).x, grid.getCellSystemCoordinates(3, 3).y, grid.getDimension(), grid.getDimension());
        }
        if (tronArena != null) {
            drawTronArena(graphics);
        }

        if (player1Bike != null) {
            graphics.setColor(PLAYER1_BIKE_COLOR);
            player1Bike.drawBike(graphics);
        }
        if (player2Bike != null) {
            graphics.setColor(PLAYER2_BIKE_COLOR);
            player2Bike.drawBike(graphics);
        }
    }
//</editor-fold>

    private void drawTronArena(Graphics graphics) {
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
                    case 2:
                        graphics.setColor(new Color(100+ Math.abs(shimmerControl), 200 + Math.abs(shimmerControl), 100 + Math.abs(shimmerControl)));
                        graphics.fillRect(GRID_ANCHOR.x + GRID_DIMENSION * col, GRID_ANCHOR.y + GRID_DIMENSION * row,
                                GRID_DIMENSION, GRID_DIMENSION);
                        break;
                }
            }
        }
    }

    private Grid grid;

    private TronBike player1Bike;
    private TronBike player2Bike;

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

    //<editor-fold desc="BikeLocationValidatorIntf Abstract Methods">
    @Override
    public BikeAndLocation validateLocation(BikeAndLocation data) {
        // Making sure the projectedLocation in inside the tronArena & the location is not in a bike trail
        if  (data.getProjectedLocation().x >= 0 && data.getProjectedLocation().x < GRID_COLS  && data.getProjectedLocation().y >= 0 && data.getProjectedLocation().y < GRID_ROWS
                && tronArena[data.getProjectedLocation().x][data.getProjectedLocation().y] == 0) {
            data.getTronBike().setLocation(data.getProjectedLocation());
        }
        return data;
    }
    //</editor-fold>

    //<editor-fold desc="AIBikeMovementIntf Abstract Methods">
    @Override
    public Point computerMove(TronBike bike) {
//        Point moveLocation = bike.getLocation();
        Point suggestedLocation = bike.getLocation();
        suggestedLocation = suggestLocation(bike);
        return suggestedLocation;
    }

    //</editor-fold>
    private Point suggestLocation(TronBike bike) {
        Point suggestedLocation;
        Direction firstDirection = bike.getDirection();
        Direction secondDirection = giveAlternateDirection(bike.getDirection());
        Direction thirdDirection = giveOppositeDirection(secondDirection);
        double roll = Math.random();

        if (roll < .95) {
            firstDirection = bike.getDirection();
        } else {
            firstDirection = giveAlternateDirection(firstDirection);
        }
        secondDirection = giveAlternateDirection(firstDirection);
        thirdDirection = giveOppositeDirection(secondDirection);

        if (locationCheck(bike.giveCoordinates(firstDirection))) {
            suggestedLocation = bike.giveCoordinates(firstDirection);
            bike.setDirection(firstDirection);
        } else if (locationCheck(bike.giveCoordinates(secondDirection))) {
            suggestedLocation = bike.giveCoordinates(secondDirection);
            bike.setDirection(secondDirection);
        } else if (locationCheck(bike.giveCoordinates(thirdDirection))) {
            suggestedLocation = bike.giveCoordinates(thirdDirection);
            bike.setDirection(thirdDirection);
        } else {
            suggestedLocation = bike.getLocation();
        }

        return suggestedLocation;
    }

    private boolean locationCheck(Point location) {
        if (location.x >= 0 && location.x < GRID_COLS && location.y >= 0 && location.y < GRID_ROWS
                && tronArena[location.x][location.y] == 0) {
            return tronArena[location.x][location.y] == 0;
        } else {
            return false;
        }
    }

    private Direction giveAlternateDirection(Direction currentDirection) {
        Direction alternateDirection = null;
        double roll = Math.random();
        switch (currentDirection) {
            case UP:
            case DOWN:
                alternateDirection = roll <= .5 ? Direction.LEFT : Direction.RIGHT;
                break;
            case LEFT:
            case RIGHT:
                alternateDirection = roll <= .5 ? Direction.UP : Direction.DOWN;
                break;
        }
        return alternateDirection;
    }

    private Direction giveOppositeDirection(Direction currentDirection) {
        Direction oppositeDirection = null;
        switch (currentDirection) {
            case UP:
                oppositeDirection = Direction.DOWN;
                break;
            case DOWN:
                oppositeDirection = Direction.UP;
                break;
            case LEFT:
                oppositeDirection = Direction.RIGHT;
                break;
            case RIGHT:
                oppositeDirection = Direction.LEFT;
                break;
        }
        return oppositeDirection;
    }




    public int getScreenWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public int getScreenHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }
}

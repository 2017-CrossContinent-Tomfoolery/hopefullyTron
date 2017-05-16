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
class hopefullyTron extends Environment {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    private static final int GRID_ROWS = 100;
    private static final int GRID_COLS = 50;
    private static final int GRID_DIMENSION = 10;
    private static final Point GRID_ANCHOR = new Point(0, 0);
//</editor-fold>
    
    public hopefullyTron() {
    }

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(GRID_ROWS, GRID_COLS, GRID_DIMENSION, GRID_ANCHOR, Color.BLACK);
    }
    
    @Override
    public void timerTaskHandler() {
    }
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
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
        }
    }
//</editor-fold>
    
    private Grid grid;
    
}

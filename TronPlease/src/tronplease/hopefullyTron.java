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

    public hopefullyTron() {
    }

    //<editor-fold defaultstate="collapsed" desc="Abstract Methods">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(100, 50, 10, new Point(0, 0), Color.BLACK);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronplease;

import java.awt.Point;

/**
 *
 * @author Benjamin
 */
public interface GridDrawData {
    public int getCellDimension();
    public int getColumns();
    public int getRows();
    
    public Point getCellSystemCoordinate(Point cellCoordinate);
}
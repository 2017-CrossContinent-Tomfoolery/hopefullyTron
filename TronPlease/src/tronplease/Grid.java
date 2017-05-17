/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tronplease;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Benjamin
 */
class Grid {
    
    //<editor-fold defaultstate="collapsed" desc="Constants">
    public static final int DEFAULT_COLUMNS = 20;
    public static final int DEFAULT_ROWS = 10;
    public static final int DEFAULT_CELL_DIMENSION = 20;
    public static final Point DEFAULT_POSITION = new Point(10, 10);
    public static final Color DEFAULT_COLOR = Color.GRAY;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    {
        setColumns(DEFAULT_COLUMNS);
        setRows(DEFAULT_ROWS);
        
        setDimension(DEFAULT_CELL_DIMENSION);
        
        setAnchor(DEFAULT_POSITION);
        setColor(DEFAULT_COLOR);
        
        setShowCellCoordinates(false);
        setCellCoordinateColor(DEFAULT_COLOR);
        setCellCoordinateFont(new Font("serif", Font.ITALIC, (DEFAULT_CELL_DIMENSION * 3 / 4)));
    }
    
    public Grid() {
    }
    
    public Grid(int columns, int rows, int dimension, Point anchor, Color color) {
        this.columns = columns; this.rows = rows; this.dimension = dimension; this.anchor = anchor; this.color = color;
    }
//</editor-fold>
    
    public void drawGrid(Graphics graphics) {
        graphics.setColor(color);
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                graphics.drawRect(anchor.x + dimension * col, anchor.y + dimension * row, 
                        dimension, dimension);
            }
        }
    }
    
    public void drawBorder(Graphics graphics) {
        graphics.setColor(color);
        graphics.drawRect(anchor.x, anchor.y, dimension * columns, dimension * rows);
    }
    
    public Point getCellSystemCoordinates(int x, int y) {
        return new Point(anchor.x + x * dimension, anchor.y + y* dimension);
    }
    
    /**
     * getCellSystemCoordinates takes a readable grid coordinate point 
     * and converts it into the coordinates on the screen. This is useful when drawing
     * @param point is the grid coordinate
     * @return the coordinates on the screen
     */
    public Point getCellSystemCoordinates(Point point) {
        return getCellSystemCoordinates(point.x, point.y);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Properties">
    private int columns;
    private int rows;
    private int dimension;
    private Point anchor;
    private Color color;
    
    private boolean showCellCoordinates;
    private Color cellCoordinateColor;
    private Font cellCoordinateFont;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the columns
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * @param columns the columns to set
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
    
    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    
    /**
     * @return the dimension
     */
    public int getDimension() {
        return dimension;
    }
    
    /**
     * @param dimension the dimension to set
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    
    /**
     * @return the anchor
     */
    public Point getAnchor() {
        return anchor;
    }
    
    /**
     * @param anchor the anchor to set
     */
    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }
    
    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * @return the showCellCoordinates
     */
    public boolean isShowCellCoordinates() {
        return showCellCoordinates;
    }
    
    /**
     * @param showCellCoordinates the showCellCoordinates to set
     */
    public void setShowCellCoordinates(Boolean showCellCoordinates) {
        this.showCellCoordinates = showCellCoordinates;
    }
    
    /**
     * @return the cellCoordinateColor
     */
    public Color getCellCoordinateColor() {
        return cellCoordinateColor;
    }
    
    /**
     * @param cellCoordinateColor the cellCoordinateColor to set
     */
    public void setCellCoordinateColor(Color cellCoordinateColor) {
        this.cellCoordinateColor = cellCoordinateColor;
    }
    
    /**
     * @return the cellCoordinateFont
     */
    public Font getCellCoordinateFont() {
        return cellCoordinateFont;
    }
    
    /**
     * @param cellCoordinateFont the cellCoordinateFont to set
     */
    public void setCellCoordinateFont(Font cellCoordinateFont) {
        this.cellCoordinateFont = cellCoordinateFont;
    }
//</editor-fold>
    
}

package net.sourceforge.jetris.gui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import net.sourceforge.jetris.figure.Figure;
import net.sourceforge.jetris.figure.FigureFactory;
import net.sourceforge.jetris.figure.Position;
import net.sourceforge.jetris.geometry.Point;

public class GridPanel extends JPanel {
	
	// Constants
	private static final String ONE_ELEMENT_CONSTRAINT = "[fill]0";
	private static final Border RAISED_BORDER = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	private static final Point LOWER_LIMIT = new Point(0,0);

	// Class attributes

	// Attributes
	protected final Point dimension;
	protected final JPanel[][] panelArray;  // TODO change JPanel[][] for new Class CellGrid[][] . CellGrid will have its own methods
	protected final Color backgroundColor;
	protected final Border emptyBorder;
	protected final Point lowerLimit;
	protected final Point upperLimit;

	// Constructors
	public GridPanel(final Point dimension, final Color backgroundColor, final Border emptyBorder) {
		this.dimension = dimension;
		this.lowerLimit = LOWER_LIMIT;
		this.upperLimit = new Point(dimension.getY()-1, dimension.getX()-1);
		this.backgroundColor = backgroundColor;
		this.emptyBorder = emptyBorder;
		this.panelArray = createPanelArray(dimension); 
		this.setLayout(createLayout());

		addComponents(panelArray);
	}

	private JPanel[][] createPanelArray(Point maxDimension) {
		int maxX = maxDimension.getX();
		int maxY = maxDimension.getY();
		JPanel[][] tmpPanelArray = new JPanel[maxX][maxY];
		for (int i=0; i<maxX; i++) {
			for (int j=0; j<maxY; j++) {
				tmpPanelArray[i][j] = createPanel(); 
			}
		}
		return tmpPanelArray;
	}
	
	private JPanel createPanel() {
		JPanel tmpPanel = new JPanel();
		tmpPanel.setBackground(backgroundColor);
		tmpPanel.setBorder(emptyBorder);
		return tmpPanel;
	}
	
	private void addComponents(Component[][] components) {
		for (Component[] componentRow : components) {
			for (int i=0; i<componentRow.length; i++) {
				String constraint = "";
				if (i==componentRow.length-1) {
					constraint = "wrap";
				}
				add(componentRow[i], constraint);
			}
		}
	}
	
	private LayoutManager createLayout() {
		GridLayout layout = new GridLayout(dimension.getX(),dimension.getY());
		return layout;
	}
	
//	private LayoutManager createLayout() {
//		String columnConstraint = createColumnConstraint(dimension.getY());
//		String rowConstraint = createRowConstraint(dimension.getX());
//		MigLayout layout = new MigLayout(
//				"fill", // Layout contraints
//				columnConstraint, // Columns contraints
//				rowConstraint); // Row contraints
//		return layout;
//	}

//	private String createRowConstraint(int rowSize) {
//		return createElementsConstraint(rowSize);
//	}
//
//	private String createColumnConstraint(int columnSize) {
//		return createElementsConstraint(columnSize);
//	}
//
//	private String createElementsConstraint(int numElements) {
//		StringBuilder stringBuilder = new StringBuilder("0");
//		for (int i=0; i<numElements; i++) {
//			stringBuilder.append(ONE_ELEMENT_CONSTRAINT);
//		}
//		return stringBuilder.toString();
//	}

	// Methods
	public synchronized void clearFigure(Figure figure) {
		Set<Point> points = figure.getOffsetedPosition().getPoints();
		Color color = backgroundColor;
		Border border = emptyBorder;
		updateGridPanel(points, color, border);
	}
	
	public synchronized void showFigureUpMedium(Figure figure) {
		Point upMediumOffset = getOffsetForUpMedium(figure);
		Figure cloneFigure = FigureFactory.getFigure(figure.getFigureId());
		cloneFigure.setOffset(upMediumOffset);
		showFigure(cloneFigure);
	}
	
	private Point getOffsetForUpMedium(Figure figure) {
		int xMaxFigure = figure.getPosition().getMaxRight();
		int xMaxGrid = upperLimit.getX() - lowerLimit.getX();
		int xOffset = Math.abs( ( xMaxGrid - xMaxFigure ) / 2 );
		int yOffset = figure.getOffset().getY(); // keep original y offset
		return new Point(xOffset, yOffset);
	}
	
	public synchronized void showFigure(Figure figure) {
		Set<Point> points = figure.getOffsetedPosition().getPoints();
		Color color = figure.getColor();
		Border border = RAISED_BORDER;
		updateGridPanel(points, color, border);
	}
	
	protected synchronized void updateGridPanel(Set<Point> points, Color color, Border border) {
		for (Point currentPoint : points) {
			updateGridPanel(currentPoint, color, border);
		}
	}

	protected synchronized void updateGridPanel(Point point, Color color, Border border) {
		setBackgroundColor(point, color);
		setBorder(point, border);
	}

	private void setBackgroundColor(Point point, Color color) {
		int x = point.getX();
		int y = point.getY();
		panelArray[y][x].setBackground(color);
	}

	private void setBorder(Point point, Border border) {
		int x = point.getX();
		int y = point.getY();
		panelArray[y][x].setBorder(border);
	}
	
	
	protected synchronized void setEmptyLine(int yCoordinate) {
		Set<Point> linePoints = new LinkedHashSet<Point>(); 
		for (int currentX=lowerLimit.getX(); currentX<upperLimit.getX(); currentX++) {
			linePoints.add(new Point(currentX, yCoordinate));
		}
		updateGridPanel(linePoints, backgroundColor, emptyBorder);
	}
	
	protected synchronized boolean isFullLine(int yCoordinate) {
    	boolean isFullLine = true;
    	int minX = lowerLimit.getX();
    	int maxX = upperLimit.getX();
    	for (int currentX = minX; currentX<=maxX; currentX++) {
    		Point point = new Point(currentX, yCoordinate);
    		if (isInGridRange(point)) {
        		if (isInEmtpyPoint(point)) {
        			isFullLine = false;
        			break;
        		}
    		}
    	}
    	return isFullLine;
    }
	
	protected synchronized void dropLine(int yCoordinate) {
		for (int currentX=0; currentX<=upperLimit.getX(); currentX++) {
			Point currentPoint = new Point(currentX, yCoordinate);
			JPanel cellGrid = panelArray[currentPoint.getY()][currentPoint.getX()];
			Color color = cellGrid.getBackground();
			Border border = cellGrid.getBorder();
			Point pointBelow = currentPoint.shiftOffset(new Point(0,+1));
			updateGridPanel(pointBelow, color, border);
		}
	}
	
	
	protected boolean isValidPoint(Point point) {
		return isInGridRange(point) && isInEmtpyPoint(point);
	}
	
	private boolean isInGridRange(Point point) {
		return point.isInRange(LOWER_LIMIT, upperLimit);
	}
	
	protected synchronized boolean isInEmtpyPoint(Point point) {
		int x = point.getX();
		int y = point.getY();
		return panelArray[y][x].getBackground().equals(backgroundColor);
	}
	
    @Override
	public synchronized String toString() {
        
        StringBuilder stringBuilder = new StringBuilder();
    	for (int y=lowerLimit.getY(); y<=upperLimit.getY(); y++) {
    		for (int x=upperLimit.getX(); x>=lowerLimit.getX(); x--) {
        		JPanel currentPanel = panelArray[y][x];
        		stringBuilder.append(getStringFromColor(currentPanel.getBackground()));
        		stringBuilder.append(" ");
        	}
        	stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getStringFromColor(Color color) {
    	String string = "X";
    	int rgb = color.getRGB();
    	if (rgb == Figure.COLOR_FIGURE_I.getRGB()) {
    		string = "R";
    	} else if (rgb == Figure.COLOR_FIGURE_T.getRGB()) {
    		string = "Y";
    	} else if (rgb == Figure.COLOR_FIGURE_O.getRGB()) {
    		string = "C";
    	} else if (rgb == Figure.COLOR_FIGURE_L.getRGB()) {
    		string = "O";
    	} else if (rgb == Figure.COLOR_FIGURE_J.getRGB()) {
    		string = "M";
    	} else if (rgb == Figure.COLOR_FIGURE_S.getRGB()) {
    		string = "B";
    	} else if (rgb == Figure.COLOR_FIGURE_Z.getRGB()) {
    		string = "G";
    	}    		
    	return string;
    }
}


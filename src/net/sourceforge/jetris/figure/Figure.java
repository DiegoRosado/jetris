package net.sourceforge.jetris.figure;
import java.awt.Color;
import java.util.Random;

import net.sourceforge.jetris.config.CommonConfig;
import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.utils.CircularArray;

public class Figure {
    
	// Constants
    public final static int FIGURE_ID_I = 0;
    public final static int FIGURE_ID_T = 1;
    public final static int FIGURE_ID_O = 2;
    public final static int FIGURE_ID_L = 3;
    public final static int FIGURE_ID_J = 4;
    public final static int FIGURE_ID_S = 5;
    public final static int FIGURE_ID_Z = 6;
    public final static int MAX_FIGURE_ID = FIGURE_ID_Z;
    
    public final static Color COLOR_FIGURE_I = Color.RED;
    public final static Color COLOR_FIGURE_T = Color.GRAY;
    public final static Color COLOR_FIGURE_O = Color.CYAN;
    public final static Color COLOR_FIGURE_L = Color.ORANGE;
    public final static Color COLOR_FIGURE_J = Color.MAGENTA;
    public final static Color COLOR_FIGURE_S = Color.BLUE;
    public final static Color COLOR_FIGURE_Z = Color.GREEN;
    
    private static final Random RANDOM = CommonConfig.getRandomGenerator();

    // Attributes
    private final CircularArray<Position> rotations;
    private final int figureId;
    private final Color color;
    private Point offset;
    private Point lastOffset;

    // Constructors
    protected Figure(int figureId, Color colorId, CircularArray<Position> rotations) {
    	this.figureId = figureId;
    	this.color = colorId;
    	this.rotations = rotations;
    	this.offset = new Point(4,0);
    	this.lastOffset = new Point(4,0);
    }
    
    // Copy constructor
    protected Figure(Figure figure) {
    	this(figure.getFigureId(), figure.getColor(), new CircularArray<Position>(figure.rotations));
    }
    
    // Methods
    // Getters
	public Position getPosition() {
		return rotations.current();
	}
	
	public Position getOffsetedPosition() {
		Position figureRawPosition = rotations.current();
		return figureRawPosition.getOffsetedPosition(this.getOffset());
	}

	public Point getOffset() {
		return offset;
	}

	public Point getLastOffset() {
		return lastOffset;
	}
    
	// Setters
    public void setLastOffset(Point lastOffset) {
		this.lastOffset = lastOffset;
	}

	// Others
    public void move(Move move) {
    	updateOffset(move);
    	if (move.equals(Move.ROTATION_RIGHT)) {
    		rotationRight();
    	}
    	if (move.equals(Move.ROTATION_LEFT)) {
    		rotationLeft();
    	}
    }
    
    private void updateOffset(Move move) {
    	updateOffset(offset.shiftOffset(move.getOffset()));
    }
    
    private void updateOffset(Point newOffset) {
    	lastOffset = offset;
    	offset = newOffset;
    }
    
	public void setOffset(Point newOffset) {
		lastOffset = offset;
		offset = newOffset;
    }
	
	public Figure getMovedFigure(Move move) {
		Figure movedFigure;
		if (move.isRotation()) {
			movedFigure = getRotatedFigure(move);
		} else {
			movedFigure = getOffsetedFigure(this.getOffset().shiftOffset(move.getOffset()));
		}
		return movedFigure;
	}
	
	private Figure getRotatedFigure(Move move) {
		Figure rotatedFigure = new Figure(this);
		if (move.equals(Move.ROTATION_RIGHT)) {
			rotatedFigure.rotationRight();
		} else { // move.equals(Move.ROTATION_LEFT)
			rotatedFigure.rotationLeft();
		}
		rotatedFigure.setOffset(this.getOffset());
		return rotatedFigure;
	}
	
	private Figure getOffsetedFigure(Point newOffset) {
		Figure offsetedFigure = new Figure(this);
		offsetedFigure.setOffset(newOffset);
		return offsetedFigure;
	}
    
	public int getHighestY() {
		int highestY = 0;
		Position currentPosition = getOffsetedPosition();
		for (Point currentPoint : currentPosition.getPoints()) {
			int currentPointY = currentPoint.getY();
			if (highestY<currentPointY) {
				highestY = currentPointY;
			}
		}
		return highestY;
	}
	
    public Position rotationRight() {
    	return rotations.next();
    }
    
    public Position rotationLeft() {
    	return rotations.previous();
    }
    
    public void rotateRandomly() {
        int numRandomRotations = RANDOM.nextInt(4);
        for (int i = 0; i < numRandomRotations; i++) {
            rotationRight();
        }
    }
    
    public int getFigureId() {
    	return figureId;
    }
    
    public Color getColor() {
    	return color;
    }

    @Override
	public String toString() {
    	return FigureFactory.getNameFromId(figureId) + " [ rotation: " + rotations.getCurrentInternalPosition() + 
    			" - Offset: " + offset + " - Offseted Position: " + this.getOffsetedPosition() + " ]";
    }
}

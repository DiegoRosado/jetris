package net.sourceforge.jetris.figure;

import net.sourceforge.jetris.geometry.Point;
import net.sourceforge.jetris.utils.CircularArray;

public class FigureZ extends Figure {

	
	// Constants
	private static final Point[] FIGURE_POINTS_0 = new Point[] { 
		   new Point(1,0),
		   new Point(0,1),
		   new Point(1,1),
		   new Point(0,2)
		}; 
	private static final Point[] FIGURE_POINTS_90 = new Point[] { 
		   new Point(0,0),
		   new Point(1,0),
		   new Point(1,1),
		   new Point(2,1)
		}; 
	private static final Point[] FIGURE_POINTS_180 = FIGURE_POINTS_0; 
	private static final Point[] FIGURE_POINTS_270 = FIGURE_POINTS_90; 

	private static final Position POSITION_0 = new Position(FIGURE_POINTS_0);
	private static final Position POSITION_90 = new Position(FIGURE_POINTS_90);
	private static final Position POSITION_180 = new Position(FIGURE_POINTS_180);
	private static final Position POSITION_270 = new Position(FIGURE_POINTS_270);

	private static final CircularArray<Position> ROTATIONS = new CircularArray<Position>(
			new Position[] { POSITION_0, POSITION_90, POSITION_180, POSITION_270} );
	

	// Attributes

	
    // Constructors
    public FigureZ() {
    	super(FIGURE_ID_Z, COLOR_FIGURE_Z, ROTATIONS);
    }
    
    // Methods


}

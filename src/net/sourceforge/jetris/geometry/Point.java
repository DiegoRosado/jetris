package net.sourceforge.jetris.geometry;

/**
 * Inmutable class
 */
public class Point {

	// Attributes
	private final int x;
	private final int y;
	
	// Constructors
	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Defensive copy constructor
	 */
	public Point(final Point point) {
		this(point.getX(), point.getY());
	}

	// Methods
	//Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Point right() {
		return new Point(x+1, y);
	}
	
	public Point left() {
		return new Point(x-1, y);
	}
	
	public Point down() {
		return new Point(x, y+1);
	}
	
	public Point up() {
		return new Point(x, y-1);
	}
	
	public Point shiftOffset(Point point) {
		return new Point(x + point.getX(), y + point.getY());
	}
	
	public boolean isInRange(Point lowerLimit, Point upperLimit) {
		boolean inXRange = x>=lowerLimit.getX() && x<=upperLimit.getX();
		if (inXRange) {
			boolean inYRange = y>=lowerLimit.getY() && y<=upperLimit.getY();
			return inYRange;
		}
		return false;
	}
	
	// equals and hashCode
    @Override
	public boolean equals(Object object) {
    	if (object==null) {
    		return false;
    	}
    	if (!(object instanceof Point)) {
    		return false;
    	} else {
    		return equals((Point)object);
    	}
    }
    
    public boolean equals(Point otherPoint) {
    	if (otherPoint==null) {
    		return false;
    	}
    	if (this==otherPoint) {
    		return true;
    	}
    	return (getX()==otherPoint.getX()) && (getY()==otherPoint.getY());
    }
	
	@Override
	public int hashCode() {
		return 31*x + 17*y;
	}
	
	// toString
	@Override
	public String toString() {
		return "Point [" + x + "," + y + "]";
	}
}

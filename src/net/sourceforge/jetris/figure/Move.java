package net.sourceforge.jetris.figure;

import net.sourceforge.jetris.geometry.Point;

public enum Move {

	NEW_FIGURE(new Point(0,0)),   // Hack, Show new figure
	RIGHT(new Point(+1,0)),
	LEFT(new Point(-1,0)),
	DOWN(new Point(0,+1)),
	UP(new Point(0,-1)),
	DROP(new Point(0,+1)),  // Hack, move DOWN recursive
	ROTATION_RIGHT(new Point(0,0)),
	ROTATION_LEFT(new Point(0,0));
		
	// Attributes
	private final Point offset;
	
	// Constructors
	private Move(final Point offset) {
		this.offset = offset;
	}
	
	// Methods
	public Point getOffset() {
		return offset;
	}
	
	public boolean isRotation() {
		return equals(ROTATION_LEFT) || equals(ROTATION_RIGHT);
	}
	
	public boolean isDownOrDrop() {
		return equals(DOWN) || equals(DROP);
	}
	
}

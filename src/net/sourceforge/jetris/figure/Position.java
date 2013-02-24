package net.sourceforge.jetris.figure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sourceforge.jetris.geometry.Point;

public class Position {
	
	// Constants
	public static final Position EMPTY_POSITION = new Position(new HashSet<Point>());
	
	// Attributes
	private final Set<Point> points;
	
	// Constructors
	public Position(final Point[] points) {
		this(new HashSet<Point>(Arrays.asList(points)));
	}
	
	public Position(Position position) {
		this.points = new HashSet<Point>(position.points);
	}
	
	public Position(Set<Point> points) {
		this.points = points;
	}
	
	// Methods
	public Set<Point> getPoints() {
		return points;
	}
	
	public Position getOffsetedPosition(Point offset) {
		Set<Point> offsetedPoints = new HashSet<Point>();
		for (Point sourcePoint : points) {
			offsetedPoints.add(sourcePoint.shiftOffset(offset));
		}
		return new Position(offsetedPoints);
	}
	
	public Position removePointsFrom(Position otherPosition) {
		Position remainingPosition = new Position(this);
		for (Point currentSourcePoint : otherPosition.getPoints()) {
			remainingPosition.removePoint(currentSourcePoint);
		}
		return remainingPosition;
	}
	
	public void removePoint(Point point) {
		points.remove(point);
	}
	
	public int getMaxRight() {
		int maxRight = Integer.MIN_VALUE;
		for (Point point : points) {
			if (point.getX()>maxRight) {
				maxRight = point.getX();
			}
		}
		return maxRight;
	}
	
	public boolean isEmpty() {
		return points.isEmpty();
	}
	
	@Override
	public String toString() {
		return points.toString();
	}

}

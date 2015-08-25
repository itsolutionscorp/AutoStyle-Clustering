import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class PointUtils {

	/**
	 * Returns the point with the largest Y value. If there are multiple such
	 * points, just chooses one arbitrarily.
	 */
	public static Point highestPoint(List<Point> points) {
		/* This is the equivalent of the initialization step mentioned in lab. */
		// TODO use the iterator to complete this method!
		Iterator<Point> pointIterator = points.iterator();
		double highestY = 0;
		Point highestPoint = new Point(0, 0);
		while (pointIterator.hasNext()) {
			Point value = pointIterator.next();
			double y = value.getY();
			if (y >= highestY) {
				highestPoint = value;
				highestY = y;
			}	
		}
		return highestPoint;
}

	/**
	 * Returns a new point that is the centroid of all the given points. A
	 * centroid has an X value that is the average of all the given points' X
	 * values, and a Y value that is the average of all the given points' Y
	 * values.
	 */
	public static Point centroid(List<Point> points) {
		Iterator<Point> pointIterator = points.iterator();
		// TODO use the iterator to complete this method!
		double totalX = 0;
		double totalY = 0;
		int counter = 0;
		while (pointIterator.hasNext()) {
			counter++;
			Point value = pointIterator.next();
			double x = value.getX();
			totalX = totalX + x;
			double y = value.getY();
			totalY = totalY + y;	
		}
		int avgX = (int) totalX / counter;
		int avgY = (int) totalY / counter;
		Point centroid = new Point(avgX, avgY);
	
		return centroid;
	}

	public static void main(String[] args) {
		List<Point> points = new LinkedList<Point>();
		points.add(new Point(1, 1));
		points.add(new Point(1, 3));
		points.add(new Point(3, 1));
		points.add(new Point(3, 3));

		/* Should be java.awt.Point[x=3,y=3] or java.awt.Point[x=1,y=3] */
		System.out.println(highestPoint(points));

		// Should be java.awt.Point[x=2,y=2]
		System.out.println(centroid(points));

		points = new LinkedList<Point>();
		points.add(new Point(1, 1));
		points.add(new Point(1, -1));
		points.add(new Point(-1, 1));
		points.add(new Point(-1, -1));

		/* Should be java.awt.Point[x=1,y=1] or java.awt.Point[x=-1,y=1] */
		System.out.println(highestPoint(points));

		// Should be java.awt.Point[x=0,y=0]
		System.out.println(centroid(points));
	}
}

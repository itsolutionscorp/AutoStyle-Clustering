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
		Iterator<Point> pointIterator = points.iterator();
		Point highestPoint = points.get(0);
		double highestY = points.get(0).getY();
		int count = 0;
		if (points != null) {
			while (pointIterator.hasNext()) {
				if (points.get(count).getY() > highestY) {
					highestPoint = points.get(count);
					highestY = points.get(count).getY();
				}
				count++;
				pointIterator.next();
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
		int totalX = 0;
		int totalY = 0;
		int avgX = 0;
		int avgY = 0;
		int count = 0;
		if (points != null) {
			while (pointIterator.hasNext()) {
				totalX += points.get(count).getX();
				totalY += points.get(count).getY();
				count++;
				pointIterator.next();
			}
			avgX = totalX / count;
			avgY = totalY / count;
		}
		return new Point(avgX, avgY);
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

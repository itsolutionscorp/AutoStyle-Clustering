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
		// TODO use the iterator to complete this method!

		Iterator<Point> startIterator = points.iterator();
		Point max = new Point(0, 0);
		Point value;

		if (startIterator.hasNext()) {
			max = startIterator.next();
		} else {
			return null;
		}

		while (pointIterator.hasNext()) {
			value = pointIterator.next();
			if (max != null && value.getY() > max.getY()) {
				max = value;
			}
		}
		return max;
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

		int sumX = 0;
		int sumY = 0;
		int counter = 0;
		Point current;
		while (pointIterator.hasNext()) {
			current = pointIterator.next();
			sumX += current.getX();
			sumY += current.getY();
			counter++;
		}

		int averageX = sumX / counter;
		int averageY = sumY / counter;

		return new Point(averageX, averageY);
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

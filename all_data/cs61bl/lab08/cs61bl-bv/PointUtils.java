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
		Point first_point = pointIterator.next();
		Point highPoint = first_point;
		double highY = first_point.getY();
		while (pointIterator.hasNext()) {
			Point curr_point = pointIterator.next();
			if (curr_point.getY() > highY) {
				highY = curr_point.getY();
				highPoint = curr_point;
			}
		}
		// TODO use the iterator to complete this method!
		return highPoint;
	}

	/**
	 * Returns a new point that is the centroid of all the given points. A
	 * centroid has an X value that is the average of all the given points' X
	 * values, and a Y value that is the average of all the given points' Y
	 * values.
	 */
	public static Point centroid(List<Point> points) {
		Iterator<Point> pointIterator = points.iterator();
		int cent_x = 0;
		int cent_y = 0;
		int count = 0;
		
		while (pointIterator.hasNext()) {
			count++;
			Point curr_point = pointIterator.next();
			cent_x += curr_point.getX();
			cent_y += curr_point.getY();
		}
		return new Point((int)cent_x/count, (int)cent_y/count);
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

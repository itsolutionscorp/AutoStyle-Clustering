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

		Point current = null;
		Point largest = null;
		if (pointIterator.hasNext()) {
			largest = pointIterator.next(); // get the first point, if any
		}
		
		while (pointIterator.hasNext()){
			current = pointIterator.next();
			if (current.getY() > largest.getY()) {
				largest = current;
			}
		}
		return largest;
	}

	/**
	 * Returns a new point that is the centroid of all the given points. A
	 * centroid has an X value that is the average of all the given points' X
	 * values, and a Y value that is the average of all the given points' Y
	 * values.
	 */
	public static Point centroid(List<Point> points) {
		Iterator<Point> pointIterator = points.iterator();
		double sumX = 0;
		double sumY = 0;
		int num = 0;
		
		while (pointIterator.hasNext()){
			Point current = pointIterator.next();
			sumX += current.getX();
			sumY += current.getY();
			++num;
		}
		
		if (num == 0) {
			System.err.println("You're passing in an empty list!");
			System.exit(-1);
		}
		return new Point((int) sumX / num, (int) sumY / num);
		// Note: http://docs.oracle.com/javase/7/docs/api/java/awt/Point.html
		//       says we don't have Point(double, double)
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

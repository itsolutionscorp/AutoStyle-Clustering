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
		boolean isFirst = true;
		int largest_y = 0;
		Point p = null;
		while (pointIterator.hasNext()){
			Point temp = pointIterator.next();
			if (isFirst){
				largest_y = temp.y;
				p = temp;
				isFirst = false;
			}
			if (temp.y > largest_y){
				p = temp;
				largest_y = temp.y;
			}
			
		}
		return p;
	}

	/**
	 * Returns a new point that is the centroid of all the given points. A
	 * centroid has an X value that is the average of all the given points' X
	 * values, and a Y value that is the average of all the given points' Y
	 * values.
	 */
	public static Point centroid(List<Point> points) {
		Iterator<Point> pointIterator = points.iterator();
		int count = 0;
		int sum_x = 0;
		int sum_y = 0;
		while (pointIterator.hasNext()){
			Point temp = pointIterator.next();
			sum_x += temp.x;
			sum_y += temp.y;
			count++;
		}
		int centroid_x = sum_x/count;
		int centroid_y = sum_y/count;
		Point p = new Point (centroid_x, centroid_y);
		return p;
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

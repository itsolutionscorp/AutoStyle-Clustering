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
		Point highestvalue = new Point(0,-100000000);
		
		while (pointIterator.hasNext()){
		    Point value = pointIterator.next();
		    if (highestvalue.getY() <= value.getY()) {
		    	highestvalue = value; 
		}
		   
	}
		return highestvalue;
	}

	/**
	 * Returns a new point that is the centroid of all the given points. A
	 * centroid has an X value that is the average of all the given points' X
	 * values, and a Y value that is the average of all the given points' Y
	 * values.
	 */
	public static Point centroid(List<Point> points) {
		Iterator<Point> pointIterator = points.iterator();
		int centroidx = 0;
		int centroidy = 0;
		int size = points.size();
		while (pointIterator.hasNext()){
		    Point value = pointIterator.next();
		    Point value2 = new Point(0,0);
		    centroidx = (int) (centroidx + value.getX() + value2.getX());
		    centroidy = (int) (centroidy + value.getY() + value2.getY());
		    value2 = value;
	}
		centroidx = centroidx/size;
		centroidy = centroidy/size;
		Point centroid = new Point(centroidx, centroidy);
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

package lab08;
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
		Point t;
		Point MaxY;
		double valueY;
		MaxY=pointIterator.next();
		valueY=MaxY.getY();
		// TODO use the iterator to complete this method!
		while (pointIterator.hasNext()){
		    t = pointIterator.next();
		    if(valueY<t.getY()){
		    	valueY=t.y;
		    	MaxY=t;
		    }
		}
		return MaxY;
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

		Point t;
		Point Centroid;
		double valueX;
		double valueY;
		int count;
		Centroid=pointIterator.next();
		count=1;
		valueX=Centroid.getX();
		valueY=Centroid.getY();
		
		// TODO use the iterator to complete this method!
		while (pointIterator.hasNext()){
		    t = pointIterator.next();
		    valueX=valueX+t.getX();
		    valueY=valueY+t.getY();
		    count++;
		}
		Centroid.x=(int)valueX/count;
		Centroid.y=(int)valueY/count;
		return Centroid;
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

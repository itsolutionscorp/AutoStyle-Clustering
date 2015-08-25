import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class PointUtilsTest extends TestCase {
	private List<Point> p;
	private Point p1 = new Point(1, 1);
	private Point p2 = new Point(2, 2);
	private Point p3 = new Point(3, 3);
	private Point p4 = new Point(1, 40);
	private Point p5 = new Point(40, 67);
	private Point p6 = new Point(-3, -100);
	private Point p7 = new Point(-20948, 809809);
	
	private Point p8 = new Point(0, 0);
	private Point p9 = new Point(10, 10);

	public void testHighestPoint() {
		this.p = new ArrayList<Point>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);
		p.add(p7);
		PointUtils pointUtil = new PointUtils();
//		System.out.println(pointUtil.highestPoint(p));
	}
	
	public void testCentroid() {
		this.p = new ArrayList<Point>();
		p.add(p8);
		p.add(p9);
		PointUtils pointUtil = new PointUtils();
		System.out.println(pointUtil.centroid(p));
	}
}

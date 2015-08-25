import java.awt.Point;

public class Line4 {

	Point[] points;

	void printLength() {
		double length;
		int dx = points[1].x - points[0].x;
		int dy = points[1].y - points[0].y;
		length = Math.sqrt(dx * dx + dy * dy);
		System.out.println("Line length is " + length);
	}

	void printAngle() {
		int dx = points[1].x - points[0].x;
		int dy = points[1].y - points[0].y;
		double angleInDegrees = Math.atan2(dy, dx) * 180.0 / Math.PI;
		System.out.println("Angle is " + angleInDegrees + " degrees");
	}

	public static void main(String[] args) {
		System.out.println("testing Line4");
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 (element 0 of points) to the point (5,
		 * 10), and initialize myLine's p2 (element 1 of points) to the point
		 * (45, 40). Print the line's length, which should be 50. Print the
		 * line's angle, which should be around 36.87 degrees.
		 */

		Line4 line4 = new Line4();
		line4.points = new Point[2];
		line4.points[0] = new Point(5, 10);
		line4.points[1] = new Point(45, 40);
		line4.printAngle();
		line4.printLength();
		

	}

}

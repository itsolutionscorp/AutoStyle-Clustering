import java.awt.Point;

public class Line4 {
    
    Point[] points = new Point[2];

    public Line4(int x1, int y1, int x2, int y2) {
        points[0] = new Point(x1, y1);
        points[1] = new Point(x2, y2);
    }

    void printLength() {
        double length;
        length = Math.sqrt (Math.pow(points[1].x - points[0].x, 2) + Math.pow(points[1].y - points[0].y, 2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (points[1].y - points[0].y , points[1].x - points[0].x) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line4");
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 (element 0 of points) to the point (5,
		 * 10), and initialize myLine's p2 (element 1 of points) to the point
		 * (45, 40). Print the line's length, which should be 50. Print the
		 * line's angle, which should be around 36.87 degrees.
		 */
        Line4 line = new Line4(5, 10, 45, 40);
        line.printLength();
        line.printAngle();
    }

}

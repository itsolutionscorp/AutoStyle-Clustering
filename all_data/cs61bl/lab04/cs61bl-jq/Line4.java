import java.awt.Point;

public class Line4 {
    
    Point[] points;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow(points[1].getY()-points[0].getY(),2)+Math.pow(points[1].getX()-points[0].getX(),2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (Math.abs(points[1].getY()-points[0].getY()),Math.abs(points[1].getX()-points[0].getX())) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line4");
        Line4 test = new Line4();
        Point[] p = new Point[2];
        p[0] = new Point(5,10);
        p[1] = new Point(45,40);
        test.points = p;
        test.printLength();
        test.printAngle();
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 (element 0 of points) to the point (5,
		 * 10), and initialize myLine's p2 (element 1 of points) to the point
		 * (45, 40). Print the line's length, which should be 50. Print the
		 * line's angle, which should be around 36.87 degrees.
		 */
    }

}

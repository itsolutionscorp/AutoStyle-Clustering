import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (p2.x - p1.x , p2.y - p1.y) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public Line2(int x1, int y1, int x2, int y2) {
        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);
    }

    public static void main(String[] args) {
        System.out.println ("testing Line2");
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's p1 to the point (5, 10), and initialize
		 * myLine's p2 to the point (45, 40). Print the line's length, which
		 * should be 50. Print the line's angle, which should be around 36.87
		 * degrees.
		 */
        Line2 line = new Line2(5, 10, 45, 40);
        line.printLength();
        line.printAngle();
    }

}

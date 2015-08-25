import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    double x;
    double y;
    
    void printLength() {
        double length;
        length = Math.sqrt ( x * x + y * y) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 ( y , x) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
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
        Point p_1 = new Point(5,10);
        Point p_2 = new Point(45, 40);
        Line2 ln = new Line2();
        ln.p1 = p_1;
        ln.p2 = p_2;
        ln.y = ln.p2.getY() - ln.p1.getY();
        ln.x = ln.p2.getX() - ln.p1.getX();
        ln.printLength();
        ln.printAngle();
    }

}

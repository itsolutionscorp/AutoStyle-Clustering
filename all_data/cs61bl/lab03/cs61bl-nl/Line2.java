import java.awt.Point;

public class Line2 {
    
    Point p1, p2;
    
    void printLength() {
        double length;
        length = Math.sqrt (Math.pow((this.p1.getX()-this.p2.getX()), 2) + Math.pow(this.p1.getY()-this.p2.getY(), 2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (Math.abs(this.p1.getY()-this.p2.getY()) , Math.abs(this.p1.getX()-this.p2.getX())) * 180.0 / Math.PI;
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
        Line2 myLine = new Line2();
        myLine.p1= new Point(5, 10);
        myLine.p2 = new Point(45, 40);
        myLine.printLength();
        myLine.printAngle();
    }

}

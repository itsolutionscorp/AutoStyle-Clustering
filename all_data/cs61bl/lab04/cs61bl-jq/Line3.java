public class Line3 {
    
    int[] coordinates;
    
    void printLength() {
        double length;
        length = Math.sqrt(Math.pow(coordinates[2]-coordinates[0],2)+Math.pow(coordinates[3]-coordinates[1],2)) ;
        System.out.println ("Line length is " + length);
    }
    
    void printAngle() {
        double angleInDegrees = Math.atan2 (Math.abs(coordinates[3]-coordinates[1]),Math.abs(coordinates[2]-coordinates[0])) * 180.0 / Math.PI;
        System.out.println ("Angle is " + angleInDegrees + " degrees");
    }

    public static void main(String[] args) {
        System.out.println ("testing Line3");
        Line3 sample = new Line3();
        int[] c = new int[4];
        c[0] = 5;
        c[1] = 10;
        c[2] = 45;
        c[3] = 40;
        sample.coordinates = c;
        sample.printLength();
        sample.printAngle();
		/*
		 * Here you should set myLine to contain a reference to a new line
		 * object. Initialize myLine's x1 and y1 (elements 0 and 1 of coords) to
		 * the point (5, 10), and initialize myLine's x2 and y2 (elements 2 and
		 * 3 of coords) to the point (45, 40). Print the line's length, which
		 * should be 50. Print the line's angle, which should be around 36.87
		 * degrees.
		 */
    }

}

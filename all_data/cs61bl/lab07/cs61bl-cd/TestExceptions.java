import java.awt.Point;
public class TestExceptions {

	public static void main (String [ ] args) {
		Point p = null;
		Object[] x = new String[7];
		Object hi = "lab07";

		System.out.println("Desired output");
		try {
			p.x = 6;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			 x[0] = new Point(5, 6);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Integer hello = (Integer) hi;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

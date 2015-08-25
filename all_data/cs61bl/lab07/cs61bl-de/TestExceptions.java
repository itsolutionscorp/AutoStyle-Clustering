import java.awt.Point;

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] x = new String[5];

		try {
			System.out.println(x[0].toString());

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object c[] = new String[3];
			c[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object y = new Integer(0);
			System.out.println((String) y);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

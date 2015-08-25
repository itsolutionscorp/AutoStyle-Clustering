/**
 * Desired output
 * 1) got null pointer
 * 2) got illegal array store
 * 3) got illegal class cast
 */

public class TestExceptions {

	public static void main (String [ ] args) {
		Object [] arr = null;

		try {
			Object x = arr[0] ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr = new String[3];
			arr[0] = new Integer(0); ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(0);
		    System.out.println((String)x);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		String[] arr = null; Object x[] = new String[3]; Object y = new Integer(0);
		
		try {
			arr[0] = "arr";

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String) y);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

/**
 * Desired output
 * 1) got null pointer
 * 2) got illegal array store
 * 3) got illegal class cast
 */

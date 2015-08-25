public class TestExceptions {
	/**
	 * Desired output
	 * 1) got null pointer
	 * 2) got illegal array store
	 * 3) got illegal class cast
	 */

	public static void main (String [ ] args) {
		Object k = null;
		Object x[] = new String[3];
		Object x1 = new Integer(0);

		try {
			k.toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0) ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String)x1); ;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

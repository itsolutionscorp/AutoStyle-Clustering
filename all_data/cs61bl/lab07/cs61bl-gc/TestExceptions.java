public class TestExceptions {
	/**
	 * Desired output
	 * 1) got null pointer
	 * 2) got illegal array store
	 * 3) got illegal class cast
	 */

	public static void main (String [ ] args) {
		Object[] test = new String[5];

		try {
			System.out.println(test[0]) ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			test[1] = "hello";
			test[1] = new Integer(1);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			test[4] = "hello";
			int x = (int) test[4];

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	/**
	 * Desired output
	 * 1) got null pointer
	 * 2) got illegal array store
	 * 3) got illegal class cast
	 */
	
	public static void main (String [ ] args) {
		
		String input;
		Object[] a;

		try {
			input = null;
			input.length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			a = new String[2];
			a[1] = 3;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			a = new Integer[2];
			a[0] = new Integer(1);
			System.out.println((String)a[0]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object x[] = new Integer[3];

		try {
			((Integer) x[0]).intValue();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Boolean(false);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String []) x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

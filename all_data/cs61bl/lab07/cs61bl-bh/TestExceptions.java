public class TestExceptions {

	public static void main (String [ ] args) {
		Object o = null;
		Object[] x;
		Object y = new Integer(0);
		try {
			o.toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x = new String[2];
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

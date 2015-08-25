public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] x = new String[2];
		try {
			x[0].toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			x[1] = (String) new Object() ;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

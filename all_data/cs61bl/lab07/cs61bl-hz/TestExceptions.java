public class TestExceptions {

	public static void main (String [ ] args) {
		Object x[] = new Integer[3];
		
		try {
			x[0].getClass();

		} catch (IllegalArgumentException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = "string";

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

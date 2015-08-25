public class TestExceptions {

	public static void main (String [ ] args) {
		int[] Array1 = null;
		Object x[] = new String[2];
		Object y = new Integer(0);

		try {
			int b = Array1.length;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			System.out.println((String)y);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}
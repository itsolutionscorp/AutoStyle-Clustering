public class TestExceptions {

	public static void main (String [ ] args) {
		
		try {
			String[] x = null;
			x.toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] y = new String[3];
			y[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object t = new TestExceptions();
			System.out.println((Double) t);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

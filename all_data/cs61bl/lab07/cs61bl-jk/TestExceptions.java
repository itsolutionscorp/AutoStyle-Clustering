public class TestExceptions {

	public static void main (String [ ] args) {
		System.out.println("The Output");

		try {
			String s = null;
			System.out.println(s.length());
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object x[] = new String[3];
		    x[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new String("123");
			System.out.println(((Integer)x).intValue());

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

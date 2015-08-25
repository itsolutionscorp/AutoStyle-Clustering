public class TestExceptions {

	public static void main (String [ ] args) {
		int[]b = null;
		Object[]c = new String[2];
		Object d = new Double(3);

		try {
			System.out.println(b.length);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			c[0] = new int[2];

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String) d);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

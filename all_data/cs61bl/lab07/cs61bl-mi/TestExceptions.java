public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] k = new String[5];

		try {
			k[0].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			k[0] = 0;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			k[0] = "hello"; System.out.print((Integer) k[0]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

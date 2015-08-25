public class TestExceptions {

	public static void main (String [ ] args) {
		Object test[] = new Integer[]{0, 1, null};

		try {
			int a = (int) test[2];

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			test[0] = 1.0;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object a = (String) test[1];

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

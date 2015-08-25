public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] arr = new String[5];

		try {
			Object x = arr[4].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr[0] = new Integer(6);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
		     Object x = new Integer(0);
		     System.out.println((String)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

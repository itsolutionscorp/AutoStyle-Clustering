public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] arr = new Integer[3];

		try {
			arr[0].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr[0] = "hi";

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			arr[0] = 1;
			System.out.println((Boolean) arr[0]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

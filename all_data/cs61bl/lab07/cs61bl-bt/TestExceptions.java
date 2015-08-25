public class TestExceptions {

	public static void main (String [ ] args) {
		Object [] arr = new String[5];
		try {
			arr[0] = null;
			arr[0].equals(5);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr[0] = 1;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new String("Hello");
			arr[1] = x;
			System.out.println((Integer)arr[1]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

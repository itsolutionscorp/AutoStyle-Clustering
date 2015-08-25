/**
 * Desired output
 * 1) got null pointer
 * 2) got illegal array store
 * 3) got illegal class cast
 */

public class TestExceptions {

	public static void main (String [ ] args) {
		String[] arr = null;
		Object[] arr2 = new String[4];
		Integer i = new Integer(3);
		try {
			arr[5] = "hi";

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
		arr2[0] = i;	

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object obj = i;
			System.out.println((String)(obj));

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}
public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] arr = new String[5];
		arr[2] = "Hello";

		try {
			arr[1].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr[0] =  new Integer (3);
			
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			((Integer) arr[2]).toString();

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

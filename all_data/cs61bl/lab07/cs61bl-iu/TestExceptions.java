public class TestExceptions {

	public static void main (String [ ] args) {
		int[] arr = null;
		Object a[] = new String[2];
		Object b = "abc";
		
		try {
			arr[0] = arr[1];
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			a[0] = new Integer(5);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			((Integer) b).intValue();

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

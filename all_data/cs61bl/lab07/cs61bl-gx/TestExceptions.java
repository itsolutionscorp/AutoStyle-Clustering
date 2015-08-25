public class TestExceptions {

	public static void main (String [ ] args) {
		Integer someInteger = null;
		int num;
		
		try {
			num = someInteger;
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
		    Object arr[] = new String[4];
		    arr[0] = new Integer(32);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object arr[] = new String[4];
			arr[0] = "a";
			System.out.println((Integer) arr[0]);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

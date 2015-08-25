public class TestExceptions {

	public static void main (String [ ] args) {
		Object x;
		Object[] arr;
		Object z;

		try {
			x = null;
			x.toString(); 

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr = new String[3];
		    arr[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			z = new Integer(3);
			System.out.println((String)z);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

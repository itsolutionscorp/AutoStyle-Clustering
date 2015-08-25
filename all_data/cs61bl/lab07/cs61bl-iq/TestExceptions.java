public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] arr = new String[4];
		String s = null;
		Object x = new Integer(4);
		
		try {
			String newS = s.substring(0, 1);
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			arr[0] = new Integer(2);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			System.out.println((String) x);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

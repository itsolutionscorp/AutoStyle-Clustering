public class TestExceptions {

	public static void main (String [ ] args) {
		String s = null;
		Object array[] = new String[5];
		Object x = new Integer(0);
		

		try {
			s.length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			array[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			 System.out.print((String) x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

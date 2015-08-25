public class TestExceptions {

	public static void main (String [ ] args) {
		String[] array = new String[5] ;

		try {
			array[0].length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			 Object x[] = new String[5];
		     x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object i = 42;
			String s = (String)i;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

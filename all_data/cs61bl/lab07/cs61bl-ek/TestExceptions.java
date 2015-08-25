public class TestExceptions {

	public static void main (String [ ] args) {

		
		try {
			int[] test = null;
			test[0] = 5;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object x[] = new String[7];
			x[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(11);
			System.out.println((String)x);
			 

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

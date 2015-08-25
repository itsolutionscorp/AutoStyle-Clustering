public class TestExceptions {

	public static void main (String [ ] args) {

		try {
			int[] a = {0,1,2,3, (Integer)null} ;
			a[3] = 4 ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object x[] = new String[3];
		     x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = 0;
		     System.out.println((String)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

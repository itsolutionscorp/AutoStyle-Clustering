public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] a = new String[5] ;

		try {
			a[0].toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			a[0] = new Integer(9) ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			a[0] = "dsadas";
			Object x = a[0];
			System.out.println((Integer)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

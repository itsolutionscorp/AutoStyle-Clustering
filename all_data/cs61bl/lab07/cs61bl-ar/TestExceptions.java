public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] a  ;


		try {
			Object o = null;
			o.toString();

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
		    Object num = new Integer(0) ;
		    System.out.println((String)num );

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

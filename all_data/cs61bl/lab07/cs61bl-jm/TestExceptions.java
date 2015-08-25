public class TestExceptions {

	public static void main (String [ ] args) {
		Object x = new Integer(10) ;
		Object[] failure = new String[1];
		Object what_am_i = null;

		try {
			 what_am_i.toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			failure[0] = 2 ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String) x) ;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] s= new String[30000];

		try {
			((String)s[0]).charAt(0) ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			s[1] = new Integer(2);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Integer n =  (Integer) ((Object)(new String()));

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

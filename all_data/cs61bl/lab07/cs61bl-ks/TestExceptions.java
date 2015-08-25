public class TestExceptions {

	public static void main (String [ ] args) {
		Object k = null;
		
		try {
			k.getClass();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			Object x[] = new String[1337];
			x[0] = new Integer(0); // from documentation website
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			Object x = new Integer[0];
			System.out.println((String) x); // from oracle
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}
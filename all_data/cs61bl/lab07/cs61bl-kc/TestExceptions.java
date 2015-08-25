public class TestExceptions {

	public static void main (String [ ] args) {
		String a = null;
		Object b[] = new String[3];
		Object c = new Integer(1);
		try {
			System.out.println(a.toString());
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			b[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String)c) ;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		String str = new String();
		String a = null;
		Object b [] = new String[3];

		try {
			System.out.println(a.length());

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			b[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(0);
			System.out.println((String)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

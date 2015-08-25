public class TestExceptions {

	public static void main (String [ ] args) {
		Object obj = null;
		Object[] z = new String[3];
		Object x = new Character('a');

		try {
			obj.hashCode();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			z[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.print((String) x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

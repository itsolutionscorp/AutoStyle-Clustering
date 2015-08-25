public class TestExceptions {

	public static void main (String [ ] args) {
		
		Object[] z = new String[2];
		Object y = "test";

		try {
			Object x = null;
			x.toString();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			z[0] = 3;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((int)y);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

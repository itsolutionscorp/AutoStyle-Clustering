

public class TestExceptions {

	public static void main (String [ ] args) {
		String a = null;
		Object x[] = new String[3];
		Object y = new Integer(0);

		try {
			a.length();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			String s = (String) y;
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}
}

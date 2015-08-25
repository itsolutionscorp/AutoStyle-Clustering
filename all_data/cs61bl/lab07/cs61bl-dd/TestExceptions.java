public class TestExceptions {

	public static void main (String [ ] args) {
		try {
			String s = null;
			s.length();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object obj[] = new String[9];
		     obj[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object obj = new String("hello");
			System.out.println((Integer) obj);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

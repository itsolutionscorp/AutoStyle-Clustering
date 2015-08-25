public class TestExceptions {

	public static void main (String [ ] args) {
		int[] a = null;
		Object b[] = new String[2];
		Object c = 7;

		try {
			System.out.println(a.length);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			b[0] = 1;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String)c);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

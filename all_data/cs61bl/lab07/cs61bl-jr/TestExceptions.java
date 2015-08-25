public class TestExceptions {

	public static void main (String [ ] args) {
		String a;
		String[] b;

		try {
			b = args;
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			a = args[0];
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			a = args[0];
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

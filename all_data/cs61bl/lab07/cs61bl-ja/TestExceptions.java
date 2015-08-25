public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] obj = new String[1];

		try {
			String s = (obj[0].toString());

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			obj[0] = 5;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((String) new Object());

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

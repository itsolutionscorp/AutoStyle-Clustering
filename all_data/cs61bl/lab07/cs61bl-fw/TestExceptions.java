public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] stringArr = new String[]{null, "a"};

		try {
			stringArr[0].toString();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			stringArr[0] = 2;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((int)stringArr[1]);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		try {
			String[] array = null;
			array[2] = "hello";
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] array = new String[5];
			array[0] = 0;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = "hello";
			System.out.println( (Double) x);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

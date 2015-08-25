public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] test = new String[2];

		try {
			test[0].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			test[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			test[0] = "test"; 
			test[0] = (Integer) test[0];

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

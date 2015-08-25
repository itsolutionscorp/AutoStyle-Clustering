public class TestExceptions {

	public static void main (String [ ] args) {
		Object nully[] = new Integer[1];

		try {
			nully[0].equals(1);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			nully[0] = new String();

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			nully[0] = (Integer) 1;
			String potatoes = (String) nully[0];

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

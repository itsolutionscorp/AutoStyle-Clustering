public class TestExceptions {

	public static void main (String [ ] args) {
		String s = null;

		try {
			s.length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			 Object[] o = new String[2];
			 o[0] = new Integer(1);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object y = "";
			Integer x = (Integer)y;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

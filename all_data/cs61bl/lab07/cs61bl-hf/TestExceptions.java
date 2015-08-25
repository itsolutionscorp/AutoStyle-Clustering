public class TestExceptions {

	public static void main (String [ ] args) {
		String S= null;
		Object[] S1= new String[2];
		Object S2= new Integer[0];
		try {
			 int a= S.length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			S1[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			 System.out.println((String)S2);;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

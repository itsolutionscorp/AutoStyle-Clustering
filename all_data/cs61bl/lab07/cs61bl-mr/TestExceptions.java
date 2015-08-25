public class TestExceptions {

	public static void main (String [ ] args) {
		Object x[] = new String[3];
		

		try {
			System.out.println(x[0].toString());

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[1]= (Integer)6;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			String s = (String)((Object)Integer.valueOf(42));

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

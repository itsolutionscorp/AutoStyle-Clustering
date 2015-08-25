public class TestExceptions {

	public static void main (String [ ] args) {
		System.out.println("Desired Output") ;

		try {
			Date a = null;
			a.isOK();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] x = new String[3];
			x[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object a = new Date(0,0,0);
			String b = (String)a;
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

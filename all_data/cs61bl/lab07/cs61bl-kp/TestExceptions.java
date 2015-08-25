public class TestExceptions {

	public static void main (String [ ] args) {
		Object [] objs = new String[5];
		try {
			objs[1].toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			objs[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			//________________ ;
			objs[2] = "Hello";
			System.out.println((Integer) objs[2]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

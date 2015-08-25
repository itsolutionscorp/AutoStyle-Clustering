public class TestExceptions {

	public static void main (String [ ] args) {
		 
		Object x[] = new Integer[]{new Integer(3), null, null};
		try {
			
			System.out.println(x[2].toString()) ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[1] = new String("hello");

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			 String y = ((String) x[0]);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

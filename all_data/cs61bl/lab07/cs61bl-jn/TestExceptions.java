public class TestExceptions {

	public static void main (String [ ] args) {
		
		try {
			Object obj = null;
	        obj.getClass();
			
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object d[] = new String[90];
			d[0] = new Integer(1);
			

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new String();
			System.out.println((Integer) x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

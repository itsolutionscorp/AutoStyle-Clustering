public class TestExceptions {

	public static void main (String [ ] args) {
		Integer num = null;

		try {
	    	int n = num.intValue();
	    	
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			Object y[] = new Integer[5];
			y[0] = new String[3];

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		
		try {
			Object z = new Integer(3);
			System.out.println((String) z);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

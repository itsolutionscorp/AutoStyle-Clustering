public class TestExceptions {
	
	/**
	 * Desired output
	 * 1) got null pointer
	 * 2) got illegal array store
	 * 3) got illegal class cast
	 */
    
	public static void main (String [ ] args) {
        Object err1 = null;
        Object[] err2 = new Double[1];
        Object err3 = 1;
        
		try {
			System.out.println(err1.equals("test"));					
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			err2[0] = "test";
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			err3 = (String) err3;
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

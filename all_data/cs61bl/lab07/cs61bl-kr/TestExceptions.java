public class TestExceptions {
	
	public static void main (String [ ] args) {
		int[] k = null ;
		try {
			k.clone();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object x[] = new String[1];
			x[0] = new int[1];
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
		Object o = new Integer(1);
		System.out.println((String)o);
	
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

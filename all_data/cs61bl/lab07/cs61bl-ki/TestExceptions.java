public class TestExceptions {

	public static void main (String [ ] args) {
		int[] test = null;
		
		try {
			int a = test.length;
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object y[] = new String[3];
			y[0]= new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(0);
			System.out.println((String)x);
		} catch (ClassCastException e) {
			
			System.out.println ("got illegal class cast");
		}
	}

}

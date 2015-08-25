public class TestExceptions {

	public static void main (String [ ] args) {
		

		try {
			Object [] arr = null;
			System.out.println (arr.toString());

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object [] x= new String[5];
			x[0] = new Double(5.4) ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(0);
			String y = (String) x;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		} 
	}

}

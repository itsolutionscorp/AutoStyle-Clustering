public class TestExceptions {

	public static void main (String [ ] args) {
		Object x[] = new String[3];
	    
		try {
			x[0].toString();
		//	System.out.println (x[0].toString);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
		//	x = new String() ;
			x[0] = new Integer(0);
		} catch (ArrayStoreException e) {
			
			 System.out.println ("got illegal array store"); 
		}
		try {
			System.out.println((int)(x[0]="string"));

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

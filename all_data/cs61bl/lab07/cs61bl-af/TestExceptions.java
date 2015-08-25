public class TestExceptions {

	public static void main (String [ ] args) {
		String ast = null;
		
		try {
			ast.indexOf('a');
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		try {
			//store out of boundary, idea from the java document
			 Object x[] = new String[5];
		     x[0] = new Integer(2);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try { 
			
			  	Object x = new Character('a');
			  	System.out.println((int)x);
		
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

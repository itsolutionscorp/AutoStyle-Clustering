public class TestExceptions {
	int K =5;

	public static void main (String [ ] args) {
		TestExceptions[] arr = new TestExceptions[5];
		TestExceptions a = new TestExceptions();
		arr[0] = a;
		

		try {
		  System.out.println(arr[1].K);
			 

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");}
		
		try {
			Object x[] = new String[3];
			 x[0] = new Integer(0);

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

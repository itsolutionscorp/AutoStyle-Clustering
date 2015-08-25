public class TestExceptions {
	String name;
	public static void main (String [ ] args) {
		

		try {
			// we want to take the length of a null as if it were an array, this will
			//cause a null pointer exception OR calling instance method of null object
			
			String[] myString = new String[5];
			myString[1].length();
			
			
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			//attempting to store the wrong type of object into an array of objects
			 Object z[] = new String[3];
		     z[1] = new Integer(0);
			
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			//attempt to cast an object to a subclass of which it is not an instance
			Object z = new Integer[4];
			System.out.println((String)z);
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

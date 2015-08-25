public class TestExceptions {

	public static void main (String [ ] args) {
		String[] myStrings = new String[5];
		myStrings[2] = "Hello";
		
		try {
			myStrings[1].length();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] objectArray = new String[4];
			objectArray[2] = new Integer(0);
			
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {

			Object someObject = new Object();
			myStrings[1] = (String) someObject;
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

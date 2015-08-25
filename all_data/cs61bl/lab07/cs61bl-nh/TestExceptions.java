public class TestExceptions {

	public static void main (String [ ] args) {
		Object obj = null ;

		try {
			obj.toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object intArray[] = new Integer[1] ;
			intArray[0] = new String("String");

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object integer = new Integer(10);
			System.out.println((String) integer);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

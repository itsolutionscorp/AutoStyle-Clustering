public class TestExceptions {

	public static void main (String [ ] args) {
		Object arr[] = new String[] { "a", "b", null};
		
		
		try {
			arr[2].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			arr[2] = 2;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object s = arr[1] ;
			int x = (int) s;
			

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

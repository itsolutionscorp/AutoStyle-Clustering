public class TestExceptions {

	public static void main (String [ ] args) {
		Object [] a =null ;

		try {
			@SuppressWarnings({ "null", "unused" })
			int b =a.length;

		} catch (NullPointerException e) {//RuntimeExcepetion
			System.out.println ("got null pointer");
		}
		try {
			a = new Integer [3];
			a[0] = new String ("abc");

		} catch (ArrayStoreException e) {//RuntimeExcepetion
			System.out.println ("got illegal array store");
		}
		try {
			Object c = new Integer (3);
			System.out.println((String)c); //Compile OK, because compiler won't test dynamic type

		} catch (ClassCastException e) {//RuntimeExcepetion
			System.out.println ("got illegal class cast");
		}
	}

}

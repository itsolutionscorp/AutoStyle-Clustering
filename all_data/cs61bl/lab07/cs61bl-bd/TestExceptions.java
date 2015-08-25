public class TestExceptions {

	public static void main (String [ ] args) {

		try {
			Integer[] array = new Integer[3];
			boolean statement = array[0].equals(array[1]);

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] array = new String[3];
			array[0] = new Integer(0);
			
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object array = new String("hello");
			System.out.println((Integer) array);
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] stringArray = new String[5];

		try {
			System.out.println(stringArray[0].toString()) ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			stringArray[0] = 4;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Integer[] integerArray = (Integer[]) stringArray;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] ints2 = new Object[6];

		
		Object[] ints = new Object[3];
		
		int[] tester = new int[3];
		try {
			ints2[0].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		
		Object y = new Object();
		try {
			tester[0] = tester[1] + 0;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			ints[0] = (String) y;
//			System.out.println((String) new Object());
		

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

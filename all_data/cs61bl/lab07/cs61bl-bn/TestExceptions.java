public class TestExceptions {

	public static void main(String[] args) {

		try {
			int[] numbers = null;
			numbers[5] = 4;
			System.out.println(numbers[5]);

		} catch (NullPointerException e) {
			System.out.println("got null pointer");
		}
		try {
			Object a[] = new String[5];
			a[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println("got illegal array store");
		}
		try {
			Object numbers = new Integer(5);
			System.out.println((String) numbers);

		} catch (ClassCastException e) {
			System.out.println("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Integer c = null;
		Object[] a = new Integer [10];
		Object b = Integer.valueOf(5);

		try {
			int i = c;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			a[6]=4.3;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			a[10]=(String) b;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

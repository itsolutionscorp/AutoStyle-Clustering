public class TestExceptions {

	public static void main (String [ ] args) {
		Object o = null;
		try {
			o.hashCode();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] a = new Integer[4];
			a[0] = 4.4;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object i = Integer.valueOf(42);
			String s = (String) i;
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

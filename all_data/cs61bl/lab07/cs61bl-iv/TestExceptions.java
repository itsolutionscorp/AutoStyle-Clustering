
/**
 * Desired output
 * 1) got null pointer
 * 2) got illegal array store
 * 3) got illegal class cast
 */

public class TestExceptions {
	public static void main (String [ ] args) {
		int[] a = null;
		Object s[] = new Integer[4];
		Object b = "s";
		
		try {
			a[0] = 2;
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			s[0] = new String("ccc");
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((int)b);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

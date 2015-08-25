public class TestExceptions {

	public static void main (String [ ] args) {
//		___________ ;

		try {
			Integer x = null;
			int n = x.intValue();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object a[] = new String[2];
			a[0] = new Double(0.0);
			
		}

		 catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object a = new Double(0.0);
			System.out.println((String) a);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

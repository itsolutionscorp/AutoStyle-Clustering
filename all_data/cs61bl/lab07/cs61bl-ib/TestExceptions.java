public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] obj = null;
		
		try {
			obj.toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			obj = new Double[3];
			obj[0] = new Integer(1);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Integer[] i = (Integer[]) obj;

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

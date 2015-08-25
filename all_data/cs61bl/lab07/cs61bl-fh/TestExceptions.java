
public class TestExceptions {

	public static void main (String [ ] args) {
		 Integer a = null;

		try {
			a.byteValue();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object arr[] = new String[2];  //Inspired by oracle docs post on illegal array store
			arr[0] = new Integer(1);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object isTrue = new Boolean(true);
			System.out.println((int) isTrue);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}





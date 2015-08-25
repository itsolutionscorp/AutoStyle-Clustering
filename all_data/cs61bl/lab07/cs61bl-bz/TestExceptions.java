import java.lang.reflect.Array;

public class TestExceptions {

	public static void main (String [ ] args) {
		Object x[] = new String[3];

		try {
			x[1].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			System.out.println((Array[])x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

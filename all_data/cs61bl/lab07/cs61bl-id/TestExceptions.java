import java.util.*;

public class TestExceptions {
	// print got null pointer 
	// got illegal array store 
	// got illegal class cast

	public static void main (String [ ] args) {
		Object a = null;
		Object y = new Integer(5);
		Object [] x = new Double[10];

		try {
			a.toString();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}

		try {
			x[0] = 5;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}

		try {
			System.out.println((String) y);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

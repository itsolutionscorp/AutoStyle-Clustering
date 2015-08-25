import java.util.*;

public class TestExceptions {

	public static void main (String [ ] args) {
		
		try {
			Integer val = null;
			int n = val.intValue();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object[] l = new Integer[5];
			l[0] = 6.5;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object random = new Integer(0);
			System.out.print((String) random);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

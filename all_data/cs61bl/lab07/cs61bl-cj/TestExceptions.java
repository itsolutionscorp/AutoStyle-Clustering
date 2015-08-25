import java.util.*;

public class TestExceptions {

	public static void main (String [ ] args) {
		
		ArrayList<String> newArrayList = new ArrayList<String>();
		Object x[] = new String[3];
		Object y = new Integer(1);
		

		

		try {
			newArrayList.add(null);
			newArrayList.get(0).toString();
			

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0] = new Integer(0);

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

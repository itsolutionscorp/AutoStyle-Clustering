import java.util.ArrayList;

public class TestExceptions {

	public static void main (String [ ] args) {
		
		try {
			ArrayList a = null;
			System.out.println(a.get(0));
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object array[] = new String[2];
			array[1]= new Integer(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x = new Integer(0);
		    System.out.println((String)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

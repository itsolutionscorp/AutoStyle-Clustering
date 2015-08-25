import java.util.*;
public class TestExceptions {

	public static void main (String [ ] args) {
		Object moppie[] = new String[5];
		moppie[1] = "hi there";
		try {
			((String)moppie[0]).length() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			moppie[0] = new Integer(0);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object bean = new String("ay");
			System.out.println((Integer) bean);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

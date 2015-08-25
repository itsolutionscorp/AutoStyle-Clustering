import java.util.ArrayList;

import com.sun.tools.javac.code.Attribute.Array;

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] x =new String[10];
		
		try {
			x[0].toString() ;
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			x[0]=new Integer(0) ;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object y = new String("a");
			System.out.print((Integer)y);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

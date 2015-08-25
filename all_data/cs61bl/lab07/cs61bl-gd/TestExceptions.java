public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] test=new String[1] ;
		test[0]=null;

		try {test[0].toString() ;

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {test[0]=new Integer(3);

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {test[0]="a";
			((Integer)test[0]).toString();
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

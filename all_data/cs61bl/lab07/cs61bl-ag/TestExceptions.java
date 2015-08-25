public class TestExceptions {
	//Referenced Java api for some ideas on how to throw these

	public static void main (String [ ] args) {
		Object[] o = new Integer[3];

		try {
			o[0].toString();

		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			 o[0]="noot noot";

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object x=new Integer(0) ;
			System.out.println((String)x);

		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}
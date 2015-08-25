
public class TestExceptions {

	public static void main (String [ ] args) {
		int [] store ;
		Object[] store1;

		try {
			Object k = null;
			k.toString();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			 store1 = new String[1];
			 store1[0] =  6;
			 
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		} 
		try {
			Object s = 356;
			System.out.println((String) s);
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		} 
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object i;
		try { 
			Object obj=null;
			obj.getClass();
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {  
			Object[] m= new String[3];
			m[0]=5;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			i="String";
			int c= (int)i;
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] newString;
		try {
			newString = null;
			System.out.println(newString[0]);
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			newString = new String[3];
			newString[0] = 4;
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			newString = new String[3];
			newString[0] = "a";
			System.out.println((Integer)newString[0]);
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

public class TestExceptions {

	public static void main (String [ ] args) {
		Object[] list = new Integer[]{1,2,3,4};

		try {
			int[] anotherlist = null;
			int k = anotherlist.length;
			
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object breakpls = "pls";
			list[0] = breakpls;

		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object breakpls = list[0];
			System.out.println((String) breakpls);
			
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

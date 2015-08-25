public class TestExceptions {

	public static void main (String [ ] args) {
		String x[] = new String[1];
		try {
			String temp = x[0];
			temp.substring(0);
			
		} catch (NullPointerException e) {
			System.out.println ("got null pointer");
		}
		try {
			Object y[] = new Integer[4];
			y[0] = new Double(0);
		} catch (ArrayStoreException e) {
			System.out.println ("got illegal array store");
		}
		try {
			Object y[] = new Integer[4];
			y[0] =  new Integer(0);
			Double z = (Double) y[0];
		} catch (ClassCastException e) {
			System.out.println ("got illegal class cast");
		}
	}

}

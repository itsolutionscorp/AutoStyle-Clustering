public class Debug {

	String myString;

	public Debug(String s) {
		myString = s;
	}

	/**
	 * Return true when myString is the result of inserting exactly one
	 * character into s, and return false otherwise.
	 */
	public boolean contains1MoreThan(String s) {
		if (myString.length() == 0) {
			return false;
		} else if (s.length() == 0) {
			return myString.length() == 1;
		} else if (myString.charAt(0) == s.charAt(0)) {
			Debug remainder = new Debug(myString.substring(1));
			return remainder.contains1MoreThan(s.substring(1));
		} else {
			//System.out.print(myString.substring(1));
			//System.out.print(s);
			return myString.substring(1) == s;
			//return myString.substring(1).equals(s);
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false
		check("abc2", "abc"); // should be true
		check("hello", "helplo"); //should be false
		check("hello", "hellop"); //should be false
		check("helplo", "hello"); //should be true
		check("hellop", "hello"); //should be true
		check("hellopp", "hello"); //should be false
		check("helplop", "hello"); //should be false
		check("1", "2"); //should be false
		check("12", "2"); //should be true
		check("21", "2"); //should be true
		check(null, null); //raises an error
		check("1", null); //raises an error
		check("1", ""); //should be true
	}

	public static void check(String s1, String s2) {
		Debug d = new Debug(s1);
		if (d.contains1MoreThan(s2)) {
			System.out.println(s1
					+ " is the result of adding a single character to " + s2);
		} else {
			System.out.println(s1
					+ " is not the result of adding a single character to "
					+ s2);
		}
	}

}
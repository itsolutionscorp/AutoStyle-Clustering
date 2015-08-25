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
			return myString.substring(1).equals(s); //fixed the code here
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false, returned false
		check("abc2", "abc"); // should be true, returned true
		check("abc","abc2"); // should be false, returned false
		check("abc23","abc"); // should be false, returned false
		check("abc23","abc3"); // should be true, returned false
		check(" 2"," "); // should be true, returned true
		check("2abc","abc"); // should be true, returned false
		check("'","31"); // should be false, returned false
		check("1",null); //CRASHED IT!
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

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
			return myString.substring(1) == s;
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false
		check("abc2", "abc"); // should be true
		check("abcde", "abc"); // should be false
		check("1234", "123"); // should be true
		check(" ", "123"); // should be false
		check("123", " "); //should be false
		check("123", "1234"); // should be false
		check("abbd", "abc"); // should be false
		check("abc ", "abc"); // should be true
		check("abc.", "abc"); // should be true
		check("   ", "  "); // should be true
		check("-1", "1"); // should be false
		check("-1", "-"); // should be true
		check(" ", ""); // should be true
		check("ICANTFINDTHEERRORS", "ICANTFINDTHEERROR"); // should be true
		check("abbc", "abc"); // should be true, but returns false
		check("ab cd", "abcd"); // should be true, but returns false
		check("abc", "bc"); // should be true, but returns false
		String a = new String("bc");
		check("a" + a, a); // should be true, but return false
		check("abcd", "dfg"); // should be false
		// check(1, 2); // should crash
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

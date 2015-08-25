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
			Debug remainder = new Debug(myString.substring(1));
			remainder.myString = remainder.myString + myString.charAt(0);
			if (remainder.myString.charAt(0) != s.charAt(0)) {
				return false;
			} else {
				remainder.myString = remainder.myString.substring(1);
				return remainder.contains1MoreThan(s.substring(1));
			}
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false
		check("abc2", "abc"); // should be true
		check("ab2c", "abc"); // should be true
		check("a2bc", "abc"); // should be true
		check("2abc", "abc"); // should be true
		check("abd5", "abc"); // should be false
		check("ab5d", "abc"); // should be false
		check("a5bd", "abc"); //should be false
		check("5abd", "abc"); // should be false
		check("abcd8", "abc"); // should be false
		check("a8bcd", "abd"); // should be false
		check("1", ""); // should be true
		check("1", " "); // should be false (the space counts)
		check ("", ""); // should be false
		check(" ", ""); // should be true
		check("", " "); // should be false
		check("a bc", "abc"); // should be true
		check("Abc", "abc"); // should be false
		check("ABC", "abc"); // should be false
		check(null, null); // should error
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

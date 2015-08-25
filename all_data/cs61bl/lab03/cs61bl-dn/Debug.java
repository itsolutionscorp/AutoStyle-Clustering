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
            // BUG: Should be .equals!
			return myString.substring(1) == s;
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false
		check("abc2", "abc"); // should be true
		
		// Our test cases
		
		check("a", "a"); // should be false
		check("ab", "a"); // should be true
		check("ba", "a"); // should be true
		
		check("abc", "a"); // should be false 
		check("ab", "bc"); // should be false
		check("abcd", "abc"); // should be true
		check("ab", "b"); // should be true
		
		check("abcde", "abde"); // good, this works now.
		
		check("", ""); // should be false
		check("a", ""); // should be true
		check("", "b"); // should be false
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

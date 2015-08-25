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
		if (myString.length() == 0) { // if there is empty string for mystring must be false 
			return false;
		} else if (s.length() == 0) { // if s is empty but my string has 1 single character must be true 
			return myString.length() == 1;
		} else if (myString.charAt(0) == s.charAt(0)) { // if the first character of both strings are the same, then you continue to check
			Debug remainder = new Debug(myString.substring(1)); // get rid of first character of mystring
			return remainder. contains1MoreThan(s.substring(1)); // get rid of first character of s 
		} else { // the first characters are different 
			return myString.substring(1) == s; // the first letter is different e.g abc and bc 
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false
		check("abc2", "abc"); // should be true
		check("cbd", "abdc"); // should be false 
		check("aabc", "abc"); // should be true 
		check("abc", "bc"); // should be true
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

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
		check("zabc", "abc"); // should be true -- fails
		check("bugs", "bgs"); // should be true -- fails
		check("abbbb", "abbb"); // should be true -- passes
		check("abcd", "abc"); // should be true -- passes
		check("bugs", "bgz"); // should be false -- passes
		check("adbc", "abc"); // should be true -- fails
		// This gave us the idea to do a direct check
		String joe = "zabc";
		String bob = "abc";
		boolean same = joe.substring(1) == bob;
		System.out.println(joe.substring(1));
		System.out.println(same);
		// And we think we have the answer
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

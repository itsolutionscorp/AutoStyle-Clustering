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
		
		System.out.println();
		check("abc", "abc");
		check("abb", "abc");
		check("abcc", "abc");
		check("abbb", "abc");
		check("abcb", "abc");
		check("bbc", "abc");
		
		System.out.println();
		check("123", "123");
		check("1234", "123");
		check("123x", "123");
		check("1223", "123");
		System.out.println("*** FALSE RESULT ABOVE ***");
		check("1123", "123");
		System.out.println("*** FALSE RESULT ABOVE ***");
		check("a123", "123");
		System.out.println("*** FALSE RESULT ABOVE ***");
		
		System.out.println();
		check("ab", "abc");
		check("abc", "ab");
		check("12c", "12");
		check("", "");
		check("x", "");
		check("a2c", "abc");
		check("a2c2", "abc");
		check(" ", " ");
		check("2", " ");
		check(" 2", " ");
		
		System.out.println();
		String c = "c";
		check("ca", c);
		String ca = "ca";
		check("cal", ca);
		check(ca, c);
		check(c, ca);
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

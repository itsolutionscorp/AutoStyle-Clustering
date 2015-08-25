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
		check("ab","cdef"); // should be false
		check("azb3","ab3"); // should be true
		check("b",""); // should be true
		check("","b"); // should be false
		check("a", "a"); // should be false
		check("abcdegabcde","abcdeabcde"); // should be true
		check("pabc","abc"); // should be true
		check("abcd","efg"); // should be false
		check("ab","b"); // should be true
		check("ab","c"); // should be false
		System.out.println("abcde" == "abcde");
		System.out.println("abcde" == "abcdf");
		System.out.println("abcde".equals( "abcde"));
		System.out.println("abcde".equals( "abcdf"));
		System.out.println("abcd".substring(1));
		System.out.println("abcd".substring(1) == "bcd");
		System.out.println("abcd".substring(1).equals("bcd"));
		String myString;
		myString = "abcde";
		String s;
		s = "abcde";
		System.out.println(myString == s);
		System.out.println(myString.equals(s));
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
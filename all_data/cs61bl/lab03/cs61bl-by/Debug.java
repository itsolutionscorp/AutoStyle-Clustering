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
			//change to: return myString.substring(1).equals(s);
		}
	}

	public static void main(String[] args) {
		check("abc", "def"); // should be false, ok
		check("abc2", "abc"); // should be true, ok
		check("a2", "2"); //should be true, but gives false
		check("ba", "a"); //should be true, but gives false
		check("abc", "ac"); //should be true, but gives false
		check("", ""); //should be false, ok
		check("a", ""); //should be true, ok
		check("abcd", "abc"); //should be true, ok
		check("abcde", "abc"); //should be false, ok
		check("a", "ab"); //should be false, ok
		check("" + "", ""); //should be false, ok
		check("", "a"); //false, ok
		check("acbca", "aba"); //false, ok
		check(" '' ", " ' "); //true, gives false
		//check("""", """);
		check("'", "");
		check("s", "s" );
		check("xtrue", "true");
		check("xremainder", "xremainder");
		check("a", null); //crashes
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

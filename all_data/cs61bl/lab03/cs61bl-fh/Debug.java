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
		check("abc", "def"); // should be false (correct)
		check("abc2", "abc"); // should be true (correct)
		check("abbc", "abc"); // should be true (incorrect)
		check("abbbc", "abc"); // should be false (correct)
		check("bbc", "bc"); // should be true (incorrect)
		check("abc", "bc"); // should be true (incorrect)
		check("abcd", "ab"); // should be false (correct)
		check("s", "as"); // should be false (correct)
		check("abc", "abbbc"); // should be false (correct)
		String myString = "hello";
		String b = "hell";
		check(myString, b); // should be true (correct)
		check("a", "a"); // should be false (correct)
		check("bbb", "bb"); // should be true (correct)
		check("abdc", "abc"); // should be true (incorrect)
		System.out.println("bbc".substring(1)); // should be bc (correct)
		System.out.println("bbc".substring(1) == "bc"); // anticipating true (incorrect)
		System.out.println("bc" == "bc"); // should be true (correct)
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

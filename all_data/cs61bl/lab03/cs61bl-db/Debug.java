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
		check("xyz4", "xyz"); // should be true 
		check("xx3yz", "x3y"); // should be false
		check("", "a"); // should be false
		check("aaa", "aaaaa"); // should be false
		check("4xyz", "xyz"); // should be true
		check("zzzx", "xxxz"); // should be false 
		check("ab3c", "abc"); // should be true
		check("a aa","aaa"); // should be true
		check("abc","aabc"); // should be false
		check("abc","abbc"); // should be false
		check("www","www"); // should be false
		check("aaaaaaaaaab","aaaaaaaaaa"); // should be true
		check("aaab","aaa"); // should be true
		check("aaaa","aaaab"); // should be true
		check("aaa!","aaa"); // should be true
		check("111a","111"); // should be
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

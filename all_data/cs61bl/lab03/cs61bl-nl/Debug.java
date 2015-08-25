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
			Debug remainder = new Debug(myString.substring(1)); //    
			return remainder.contains1MoreThan(s.substring(1)); //
		} else {
			return myString.substring(1) == s; //
		}
	}

	public static void main(String[] args) {
		check("abc", "def");  // should be false
		check("abc2", "abc"); // should be true
		check("abc", "abc2"); // correctly returns true
		check("abd2", "abc"); // correctly returns false
							  // incorrectly returns true(no pairs of strings)
		check("ab2c", "abc"); // incorrectly returns false
//		check(23,2);          // crash case
//		String d = "abc";
//		System.out.println(d.substring(1));
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

/* bug.info
 * 1. check("abc", "abc2"); // correctly returns true
2. check("abd2", "abc"); // correctly returns false
3. no pairs of strings   // incorrectly returns true(no pairs of strings)
4. check("ab2c", "abc"); // incorrectly returns false
5. check(23,2);          // crash case

Bug explain:
else if (myString.charAt(0) == s.charAt(0)) {
	Debug remainder = new Debug(myString.substring(1)); 	return remainder.contains1MoreThan(s.substring(1)); 
}
This "else if" condition only checks letters in order. It only returns true when letter is inserted at the end of the string. For example, "abc2" and "abc" returns true, but "ab2c" and "abc" returns false.  */

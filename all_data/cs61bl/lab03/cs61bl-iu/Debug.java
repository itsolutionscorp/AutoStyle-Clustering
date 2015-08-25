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
		
		// our own tests 
		
		//more adding in the back
		check("abc", "abc2"); // should be false
		check("1234", "123"); // should be true 
		check("ab", "a"); // should be true
		check("ab", "ab"); // should be false
		check("abcd", "ab"); // should be false
		
		//adding in the front 
		check("aabc", "abc"); // should be true 
		check("4123", "123"); // should be true 
		
		//adding in the middle 
		check("123", "13"); // should be true
		check("abc", "ac"); // should be true
		
		//adding repeats 
		check("aa", "a"); // should be true
		check("aaa", "a"); // should be false
		check("11", "1"); // should be true
		
		//checking null as parameters
		check(null, null); // should crash 
		
	
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

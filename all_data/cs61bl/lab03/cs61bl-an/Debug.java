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
		if (myString.length() == 0) { //s2 is empty, so cant be longer than s1
			return false;
		} else if (s.length() == 0) { //s1 is zero so s2 must be one length
			return myString.length() == 1;
		} else if (myString.charAt(0) == s.charAt(0)) { //if first letter of s2 is the first letter s1
			Debug remainder = new Debug(myString.substring(1)); //calls debug on mystring becomes itself - first char
			return remainder.contains1MoreThan(s.substring(1)); //does it again for everything one shorter
		} else {
			return myString.substring(1) == s; //does s2 from 2nd letter on equal s1
		}
	}

	public static void main(String[] args) {
		
		check("a", ""); //should be true
		check("abc2", "abc"); // should be true
		
		check("abc", "def"); // should be false
		check("ab", "abc"); //should be false
		check("abcde", "abc"); //should be false
		check("abc", "abc"); //should be false
		check("","a"); //should be false
		
		check("1abc","abc"); //incorrectly false
		check("abdc", "abc"); //incorrectly false
		
		check("\"",""); //incorrectly true
		
		//  check("\\t","\	"); //crashes
		//	check("'\","'"); //crashes
		//	check('\\' ,'\'); //crashes
		
		///other checks
		check("00000000", "000000001");
		check("00000001", "0000000");
		check("a","");
		check(" a"," ");
		check("a ", "a");
		check("and","an");
		check("\011","\01"); 
		check("null","null");
	
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

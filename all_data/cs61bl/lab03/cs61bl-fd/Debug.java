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
		check("ab1c", "abc"); // should be false
		
		//when s is "",and myString is a single character
		System.out.println("when s is '',and myString is a single character");
		System.out.println("----------------------------------------");
		check(" ","");
		check("a","");
		check("1","");
		check("@","");
		System.out.println("----------------------------------------");
		System.out.println(""); //should return true and it does.
		
		
		
		//when s is "",but myString is more than a letter
		System.out.println("when s is '',but myString is more than a letter");
		System.out.println("----------------------------------------");
		check("  ","");
		check("ab","");
		check("12","");
		
		System.out.println("----------------------------------------");
		System.out.println(""); //should return false and it does.
		
		//when s is added at the end of myString
		System.out.println("when s is added at the end of myString");
		System.out.println("----------------------------------------");
		check("abc2", "abc"); 
		check("iii2", "iii"); // should be true and returns true
		String a= "abc2";
		String b= "abc";
		check(a,b);
		System.out.println("----------------------------------------");
		System.out.println("");
		
		//when s is added to the beginning or in the middle
		System.out.println("when s is added to the beginning or in the middle");
		System.out.println("----------------------------------------");
		check("1abc","abc");
		check("a1bc","abc");
		check("4123", "123"); // should be true but returns false
		System.out.println("----------------------------------------");
		System.out.println("");
		
		
		// when myString is ""
		System.out.println("when myString is empty(not null)");
		System.out.println("----------------------------------------");
	    check("", " ");
		check("", "");
		check("","1");
		check("","a");
		check("","@");
		check("","abc"); // should be false and returns false
		System.out.println("----------------------------------------");
		System.out.println("");
		
		
		
		
		
		
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

package lab01;

public class YearStuff {
	// this is one kind of comment
	/* This is a multi-line comment*/
	/** This is a
	 * multi-line
	 * comment */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int year = 2003;
		if ( year % 400 == 0)
			System.out.println(year + " is s leap year.");
	    else if (year % 4 == 0 && year % 100 != 0) {
			System.out.println (year + " is a leap year.");
		} else
			System.out.println (year + " is not a leap year.");

	}

}

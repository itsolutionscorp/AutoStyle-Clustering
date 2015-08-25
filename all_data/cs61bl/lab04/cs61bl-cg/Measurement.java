public class Measurement {
	
	private int myFeet;
	private int myInches;

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		myFeet = 0;
        myInches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		if (feet >= 0) {
			myFeet = feet;
		} else {
			myFeet = 0;
			System.out.println("Please enter non-negative value for feet.");
		}
		
		myInches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		if (feet >= 0 && inches >= 0) {
			myFeet = feet + inches/12;
			myInches = inches%12;
		} else {
			System.out.println("Please enter non-negative values for feet and inches.");
			myFeet = 0;
			myInches = 0;
		}
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return myFeet; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return myInches; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int t1 = this.myFeet*12 + this.myInches;
		int t2 = m2.myFeet*12 + m2.myInches;
		Measurement tp = new Measurement();
		tp.myFeet = (t2 + t1)/12;
		tp.myInches = (t2 + t1)%12;
		return tp;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int t1 = this.myFeet*12 + this.myInches;
		int t2 = m2.myFeet*12 + m2.myInches;
		Measurement tp = new Measurement();
		if (t1 >= t2) {			
			tp.myFeet = (t1 - t2)/12;
			tp.myInches = (t1 - t2)%12;
			return tp;
		} else {
			System.out.println("Cannot substract larger measurement.");
			return tp;
		}
		 
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		Measurement m = new Measurement(); 
		if (multipleAmount < 0) {
			System.out.println("Please enter non-negative argument.");
			return m;  
		} else {
			m.myFeet = this.myFeet + (this.myInches * multipleAmount)/12;
			m.myInches = (this.myInches * multipleAmount)%12;
			return m;
		}
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return this.myFeet + "f'" + this.myInches + "i\""; 
	}

}

public class Measurement {
	
	private int myfeet;
	private int myinches;
	
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		myfeet = 0;
		myinches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		myfeet = feet;
		myinches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		myfeet = feet;
		if (inches >= 12) {
			myfeet = myfeet + inches/12;	// bug in case you input more than 11 for inches
			myinches = inches%12;
		} else {
			myinches = inches;
		}
		
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return myfeet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return myinches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int totalInches = this.getInches() + (this.getFeet()*12) + m2.getInches() + (m2.getFeet()*12);
		int newFeet = totalInches/12;		// get added feet
		int newInches = totalInches%12;		// get remainder inches for new measurement
		Measurement newReturn = new Measurement(newFeet, newInches);
		return newReturn;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int totalInches = this.getInches() + (this.getFeet()*12) - ( m2.getInches() + m2.getFeet()*12);
		int newFeet = totalInches/12;
		int newInches = totalInches%12;
		Measurement newReturn = new Measurement(newFeet, newInches);
		return newReturn;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int totalInches = (this.getInches() + this.getFeet()*12) * multipleAmount;
		int newFeet = totalInches/12;
		int newInches = totalInches%12;
		Measurement newReturn = new Measurement(newFeet, newInches);
		return newReturn;
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return new String(myfeet + "\'" + myinches + "\"");
	}

}

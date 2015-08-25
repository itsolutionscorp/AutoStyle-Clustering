public class Measurement {
	private int myFeet;
	private int myInches;
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		this.myFeet = 0;
		this.myInches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.myFeet = feet;
		this.myInches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.myFeet = feet;
		while (inches >= 12) {
			inches -= 12;
			myFeet++;
		}
		this.myInches = inches;
	}


	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return this.myFeet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return this.myInches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int newFeet = this.myFeet + m2.myFeet;
		int newInches = this.myInches + m2.myInches;
		Measurement newMeasure = new Measurement(newFeet, newInches);
		return newMeasure;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int newFeet = this.myFeet - m2.myFeet;
		int newInches = this.myInches - m2.myInches;
		if (newInches < 0) {
			newFeet--;
			newInches += 12;
		}
		return new Measurement(newFeet, newInches);
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int newFeet = this.myFeet * multipleAmount;
		int newInches = this.myInches * multipleAmount;
		return new Measurement(newFeet, newInches);
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return new String(this.myFeet + "\'" + this.myInches + "\""); 
	}

}

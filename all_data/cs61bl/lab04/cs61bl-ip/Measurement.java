public class Measurement {
	
	private int MAX_CONSTANT = 12;
	private int myInches;
	
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		this.myInches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.myInches = feet * MAX_CONSTANT;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.myInches = feet * MAX_CONSTANT + inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return getFeet(this.myInches);
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return getInches(this.myInches);
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int m2Inches = m2.getFeet() * MAX_CONSTANT + m2.getInches();
		int total = this.myInches + m2Inches;
		return new Measurement(0, total);
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int m2Inches = m2.getFeet() * MAX_CONSTANT + m2.getInches();
		int total = this.myInches - m2Inches;
		return new Measurement(0, total);
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		return new Measurement (0, this.myInches * multipleAmount);
	}
	
	/**
	 * Gets the measurement feet from the total number of inches.
	 */
	private int getFeet(int inches) {
		return inches / MAX_CONSTANT;
	}
	
	/**
	 * Gets the measurement inches from the total number of inches
	 */
	private int getInches(int inches) {
		return inches % MAX_CONSTANT;
	}
	
	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return this.getFeet() + "'" + this.getInches() + "''";
	}

}

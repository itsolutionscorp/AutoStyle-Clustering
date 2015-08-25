public class Measurement {
	
	private int myFeet = 0;
	private int myInches = 0;
	
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		myFeet = feet;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		myFeet = feet;
		myInches = inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return myFeet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return myInches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int holdFeet = this.getFeet() + m2.getFeet();
		int holdInches = this.getInches() + m2.getInches();
		holdFeet = holdFeet + holdInches / 12;
		holdInches = holdInches % 12;
		return new Measurement(holdFeet, holdInches);
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int m1Inches = this.getInches() + this.getFeet() * 12;
		int m2Inches = m2.getInches() + m2.getFeet() * 12;
		int m3Inches = m1Inches - m2Inches;
		return new Measurement(m3Inches / 12, m3Inches % 12); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int holdFeet = this.getFeet() * multipleAmount;
		int holdInches = this.getInches() * multipleAmount;
		holdFeet = holdFeet + holdInches / 12;
		holdInches = holdInches % 12;
		return new Measurement(holdFeet, holdInches);
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		int holdFeet = this.getFeet();
		int holdInches = this.getInches();
		return new String(Integer.toString(holdFeet) + "'" + Integer.toString(holdInches) + "\"");
	}

}

public class Measurement {

	private int ft;
	private int in;
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */

	
	public Measurement() {
		this.ft = 0;
		this.in = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.ft = feet;
		this.in = 0;
	
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {

		this.ft = feet;
		this.in = inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return this.ft; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return this.in; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int thisTotalInches = this.getInches() + (12 * this.getFeet());
		int m2TotalInches = m2.getInches() + (12 * m2.getFeet());
		int m3TotalInches = m2TotalInches + thisTotalInches;
		int m3Inches = m3TotalInches % 12;
		m3TotalInches = m3TotalInches - m3Inches;
		int m3Feet = m3TotalInches/12;
		return new Measurement(m3Feet, m3Inches); // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int thisTotalInches = this.getInches() + (12 * this.getFeet());
		int m2TotalInches = m2.getInches() + (12 * m2.getFeet());
		int m3TotalInches = thisTotalInches - m2TotalInches;
		int m3Inches = m3TotalInches % 12;
		m3TotalInches = m3TotalInches - m3Inches;
		int m3Feet = m3TotalInches/12;
		return new Measurement(m3Feet, m3Inches); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int thisTotalInches = this.getInches() + (12 * this.getFeet());
		int newTotalInches = thisTotalInches * multipleAmount;
		int newInches = newTotalInches % 12;
		newTotalInches = newTotalInches - newInches;
		int newFeet = newTotalInches / 12;
		return new Measurement(newFeet, newInches); // provided to allow the file to compile
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		String s= new String(this.getFeet() + "'" + this.getInches() + "''");
		return s; // provided to allow the file to compile
	}

}

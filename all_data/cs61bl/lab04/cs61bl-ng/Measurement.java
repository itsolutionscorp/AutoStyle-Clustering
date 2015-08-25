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
		myFeet = feet;
		myInches = 0;
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
		Measurement m = new Measurement(this.myFeet, this.myInches);
		m.myFeet += m2.getFeet();
		m.myInches += m2.getInches();
		int more_feet = 0;
		while (m.myInches >= 12) {
			more_feet += 1;
			m.myInches -= 12;		
		}
		m.myFeet += more_feet;
		return m;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement m = new Measurement(this.getFeet(), this.getInches());
		m.myFeet -= m2.getFeet();
		m.myInches -= m2.getInches();
		while (m.myInches < 0) {
			m.myFeet -= 1;
			m.myInches += 12;
		}
		return m;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int total_inches = 12 * this.getFeet() + this.getInches();
		Measurement m = new Measurement(0, total_inches * multipleAmount);
		int more_feet = 0;
		while (m.myInches >= 12) {
			more_feet += 1;
			m.myInches -= 12;		
		}
		m.myFeet = more_feet;
		return m;
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return this.getFeet() + "\'" + this.getInches() + "\"";
	}

}

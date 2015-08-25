public class Measurement {
	int myFeet;
	int myInches;
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
		int AddedFeet = this.getFeet() + m2.getFeet();
		int AddedInches = this.getInches() + m2.getInches();
		if (AddedInches > 12) {
			AddedFeet = AddedFeet + AddedInches / 12;
			AddedInches = AddedInches % 12;
		}
		this.myInches = AddedInches;
		this.myFeet = AddedFeet;
		return this;
		}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int minusFeet = this.getFeet() - m2.getFeet();
		int minusInches = this.getInches() - m2.getInches();
		if (minusInches < 0) {
			minusInches = 12 - m2.getInches() + this.getInches();
			minusFeet = minusFeet - 1;
		}
		this.myInches = minusInches;
		this.myFeet = minusFeet;
		return this;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int multFeet = this.getFeet() * multipleAmount;
		int multInches = this.getInches() * multipleAmount;
		if (multInches >= 12) {
			multFeet = multFeet + multInches / 12;
			multInches = multInches % 12;
		}
		this.myInches = multInches;
		this.myFeet = multFeet;
		return this; 
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		System.out.println(this.getFeet() + "" + "'" + this.getInches() + "\"");
		return this.getFeet() + "" + "'" + this.getInches() + "\"";
	}

}

public class Measurement {

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	private int Feet;
	private int Inches;
	public Measurement() {
		this.Feet = 0;
		this.Inches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.Feet = feet;
		this.Inches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.Feet = feet;
		this.Inches = inches;
		while (this.Inches >= 12) {
			this.Inches = this.Inches -12;
			this.Feet++;
		}
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return this.Feet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return this.Inches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int newInches;
		int newFeet;
		newInches = this.Inches + m2.Inches;
		newFeet = this.Feet + m2.Feet;
		while (newInches >= 12) {
			newInches = newInches - 12;
			newFeet++;
		}
		Measurement newMeasurement = new Measurement(newFeet, newInches);
		return newMeasurement;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int newInches;
		int newFeet;
		newInches = this.Inches - m2.Inches;
		newFeet = this.Feet - m2.Feet;
		while (newInches < 0) {
			newInches = newInches + 12;
			newFeet--;
		}
		Measurement newMeasurement = new Measurement(newFeet, newInches);
		return newMeasurement;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int newInches;
		int newFeet;
		newInches = this.Inches * multipleAmount;
		newFeet = this.Feet * multipleAmount;
		while (newInches >= 12) {
			newInches = newInches - 12;
			newFeet++;
		}
		Measurement newMeasurement = new Measurement(newFeet, newInches);
		return newMeasurement;
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return new String(this.Feet + "'" + this.Inches + '"');
	}

}

public class Measurement {
	int feet;
	int inches;
	int totalinches;
	
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		this.feet = 0;
		this.inches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.feet = feet;
		this.inches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.feet = feet;
		this.inches = inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return this.feet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return this.inches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		Measurement newMeasurement = new Measurement();
		newMeasurement.feet = this.feet + m2.feet;
		newMeasurement.inches = this.inches + m2.inches;
		newMeasurement.feet = newMeasurement.feet + newMeasurement.inches/12;
		newMeasurement.inches = newMeasurement.inches % 12;
		return newMeasurement;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement newMeasurement = new Measurement();
		this.totalinches = 12 * this.feet + this.inches;
		m2.totalinches = 12 * m2.feet + m2.inches;
		newMeasurement.totalinches = this.totalinches - m2.totalinches;
		newMeasurement.feet = newMeasurement.totalinches / 12;
		newMeasurement.inches = newMeasurement.totalinches % 12;
		return newMeasurement;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
2	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		Measurement newMeasurement = new Measurement();
		this.totalinches = 12 * this.feet + this.inches;
		this.totalinches = multipleAmount * this.totalinches;
		newMeasurement.feet = this.totalinches / 12;
		newMeasurement.inches = this.totalinches % 12;
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
		String newString = new String();
		newString = this.feet + "'" + this.inches + "\"";
		return newString;
	}

}

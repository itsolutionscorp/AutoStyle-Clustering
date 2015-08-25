public class Measurement {
	
	int feet;
	int inches;

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
		this.feet = feet;

	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.feet = feet + inches/12;
		this.inches = inches%12;

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
		Measurement sum = new Measurement();
		sum.feet = m2.feet + this.feet;
		if (m2.inches + this.inches >= 12) {
			sum.feet++;
			sum.inches = m2.inches + this.inches - 12;
		} else {
			sum.inches = m2.inches + this.inches;
		}
		return sum;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement difference = new Measurement();
		difference.feet = this.feet - m2.feet;
		if (m2.inches > this.inches) {
			difference.inches = 12 - (m2.inches - this.inches);
			difference.feet--;
		} else {
			difference.inches = this.inches - m2.inches;
		}
		return difference;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		Measurement product = new Measurement();
		product.feet = multipleAmount*this.feet + multipleAmount*this.inches/12;
		product.inches = (multipleAmount*this.inches)%12;
		return product;
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return (this.feet + "' " + this.inches + "\"");
	}
}

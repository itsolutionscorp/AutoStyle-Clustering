public class Measurement {

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	private int feet;
	private int inches;
	
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
		this.feet = feet;
		this.inches = inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return feet; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return inches; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int countinches = this.getFeet() * 12 + this.getInches() + m2.feet*12 + m2.inches;		
		this.feet = countinches / 12;
		this.inches = countinches % 12;
		return this;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int countInches1 = (this.getFeet() * 12 + this.getInches());
		int countInches2 = (m2.feet*12 + m2.inches);
		if (countInches2 > countInches1) {
			System.out.println("Invalid Arguments");
			return this;			
		} else {
		countInches1 = countInches1 - countInches2;
		this.feet = countInches1 / 12;
		this.inches = countInches1 % 12;
		return this; 
		}
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		if (multipleAmount < 0) {
			System.out.println("Bad Arguments");
			return this;
		}
		int countInches = this.getFeet() * 12 + this.getInches();
		countInches = countInches * multipleAmount;
		this.feet = countInches / 12;
		this.inches = countInches % 12;
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
		String s = new String();
		s = this.feet + "'" + this.inches + "\"";
		return s;
	}

}

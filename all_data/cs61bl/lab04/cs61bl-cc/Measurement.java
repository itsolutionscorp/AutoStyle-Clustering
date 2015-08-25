public class Measurement {
	
	
	private int inches;
	private int feet;
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		this.inches = 0;
		this.feet = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.inches = 0;
		this.feet = feet;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.inches = inches;
		this.feet = feet;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return this.feet; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return this.inches; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int newInches = this.inches + m2.inches;
		int newFeet = this.feet + m2.feet; 
		while (newInches >= 12){
			newInches = newInches - 12;
			newFeet = newFeet + 1;
		} 
		return new Measurement(newFeet, newInches); // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int newInches = this.inches - m2.inches;
		int newFeet = this.feet - m2.feet; 
		if (newInches < 0){
			newFeet = newFeet-1;
			newInches = newInches + 12; 
		}
		return new Measurement(newFeet, newInches); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int totalInches = this.feet*12 + this.inches;
		int newTotalInches = totalInches*multipleAmount;
		int newFeet = 0;
		while (newTotalInches >= 12){
			newTotalInches = newTotalInches - 12;
			newFeet = newFeet + 1;
		} 
		return new Measurement(newFeet, newTotalInches); // provided to allow the file to compile
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		String newString = this.feet + "\'" + this.inches + "\"";
		return newString; // provided to allow the file to compile
	}

}

public class Measurement {
	
	private int feet; 
	private int inches; 
	
	final static private int UNITS = 12; //static - shared by all instances of class 

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		feet = 0;
		inches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.feet = feet;
		inches = 0;
		convert(this); // converts argument to appropriate unit/initialization 
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		this.feet = feet;
		this.inches = inches;
		convert(this); // converts argument to appropriate unit/initialization
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return feet;
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return inches;
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		Measurement copy = new Measurement(feet, inches);
		copy.feet += m2.feet;
		copy.inches += m2.inches;
		convert(copy);
		return copy; // "this" is a reference to current object  
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement copy = new Measurement(feet, inches);
		copy.feet -= m2.feet;
		copy.inches -= m2.inches;
		convert(copy);
		return copy; // "this" is a reference to current object  
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		Measurement copy = new Measurement(feet, inches);
		copy.inches += UNITS * copy.feet;
		copy.feet = 0; 
		copy.inches = copy.inches * multipleAmount; 
		convert(copy);
		return copy; // "this" is a reference to current object 
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		convert(this); 
		return feet + "'" + inches + "\""; 
	}
	
	/**
	 * convert object to appropriate units 
	 */
	private void convert(Measurement arg) {
		while (arg.inches < 0) {
			arg.inches += UNITS; 
			arg.feet -= 1; 
		}
		
		while (arg.inches >= UNITS) {
			arg.inches -= UNITS; 
			arg.feet += 1; 
		}
	}

}

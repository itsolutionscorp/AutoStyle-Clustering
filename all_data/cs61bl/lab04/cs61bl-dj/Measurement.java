public class Measurement {

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	
	int f;
	int i;
	
	public Measurement() {
		f = 0;
		i = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		f = feet;
		i = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		f = feet;
		i= inches;
	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return f; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return i; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		this.f += m2.f;
		if (this.i + m2.i > 11) {
			this.f += 1;
			this.i += (m2.i-12);
		} else {
			this.i += m2.i;
		}
		
		return this; // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		this.f -= m2.f;
		int difference = (this.i - m2.i);
		if (difference < 0) {
			this.f -= 1;
			this.i = (12 + difference);
			//this.i -= (12 - difference);
		} else {
			this.i -= m2.i;
		}
		
		return this;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		this.f *= multipleAmount;
		int product = this.i*multipleAmount;
		if (product > 11) {
			this.f += product/12;
			this.i = product%12;
		} else {
			this.i *= multipleAmount;
		}
		
		return this; // provided to allow the file to compile
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return this.f + "'" + this.i + "\""; 
	}
	
	public boolean equals(Object m) {
		if (this.f == ((Measurement) m).f && this.i == ((Measurement)m).i) {
			return true;
		} else {
			return false;
		}
		
	}
	
}

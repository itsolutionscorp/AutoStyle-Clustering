public class Measurement {

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	int ft;
	int inch;
	
	public Measurement() {
		this.ft = 0;
		this.inch = 0;

	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		this.ft = feet;
		this.inch = 0;

	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		if (inches >= 12 ){
			this.inch = inches - 12* ((int) (inches / 12));
			System.out.println(this.inch);
			this.ft = feet + (int) (inches  / 12);
			System.out.println(this.ft);
		
		}
		else {
		
		this.ft = feet;
		this.inch = inches;
		}

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
		return this.inch; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		this.ft = this.ft + m2.ft;
		this.inch = this.inch + m2.inch;
		return new Measurement(this.ft, this.inch); // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		this.ft = this.ft -m2.ft;
		this.inch = this.inch - m2.inch;
		return new Measurement(this.ft, this.inch); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		this.inch = this.inch * multipleAmount;
		this.ft = this.ft * multipleAmount;
		
		return new Measurement(this.ft, this.inch); // provided to allow the file to compile
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return "this.getFeet()" + "'" + "this.getInches()";    // provided to allow the file to compile
	}	

}

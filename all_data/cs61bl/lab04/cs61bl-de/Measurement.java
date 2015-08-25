public class Measurement {
	private int myFeet;
	private int myInches;

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
		myFeet = feet;

	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) { 
		if (inches >= 12) {
			myFeet = feet + (int) inches/12; 
			myInches =inches%12;
		
		}else {
			myFeet = feet;
			myInches = inches;
		}

	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return myFeet; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return myInches; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public void plus(Measurement m2) {
		this.myInches = this.getInches() + m2.getInches(); //21
		this.myFeet = this.getFeet() + m2.getFeet(); //9
		
		if (this.myInches>12) {
			this.myFeet = this.myFeet + (int) this.myInches/12;
			this.myInches = this.myInches%12;		
		}		
			
		}
		

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public void minus(Measurement m2) {
		int diff = this.myInches - m2.myInches;
		this.myFeet -= m2.myFeet;
		if (diff <0) {
			this.myFeet --;
			this.myInches = 12 + diff;
		}else {
			this.myInches -= m2.myInches;
		}
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public void multiple(int multipleAmount) {
		this.myFeet *= multipleAmount;
		this.myInches *= multipleAmount;
		
		
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		String rep = this.getFeet() + "'" + this.getInches() + "\"";
		return rep;
	}

}

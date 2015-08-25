public class Measurement {
	private int myInches;
	private int myFeet;

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		myInches = 0;
		myFeet = 0;

	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		myInches = 0;
		myFeet = feet;
		

	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		if (inches>11){
			myInches = inches%12;
			myFeet = feet + inches/12;
		}
		else{
			myInches = inches;
			myFeet = feet;
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
	public Measurement plus(Measurement m2) {
		int feet = 0;
		int inches = 0;
		feet = myFeet + m2.myFeet;
		inches = myInches+ m2.myInches;
		if (inches>11){
			feet += inches/12;
			inches += inches%12;
			
		}
		return new Measurement(feet, inches);
		
		
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int inches = 0;
		inches = myFeet *12 + myInches - m2.myInches - m2.myFeet*12;
		return new Measurement(inches/12, inches%12);
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int inches = (myInches + myFeet*12) * multipleAmount;
		return new Measurement(inches/12, inches%12);
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return "" +myFeet +"'" +myInches +"\"";
	}

}

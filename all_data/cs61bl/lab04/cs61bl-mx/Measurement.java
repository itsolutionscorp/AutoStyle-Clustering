public class Measurement {
	private int myFeet;
	private int myInches;
	

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	public Measurement() {
		myFeet = 0;
		myInches = 0;
	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		myFeet = feet;
		myInches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		myFeet = feet;
		myInches = inches;
		while (myInches >= 12) { 
			myFeet = myFeet + 1;
			myInches = myInches - 12;
				
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
		Measurement newMeasurement = new Measurement();
		newMeasurement.myFeet = this.myFeet + m2.myFeet;
		newMeasurement.myInches = this.myInches + m2.myInches;
		while (newMeasurement.myInches >= 12) {
			newMeasurement.myFeet = newMeasurement.myFeet + 1;
			newMeasurement.myInches = newMeasurement.myInches - 12;
		}
		return newMeasurement;
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement newMeasurement = new Measurement();
		newMeasurement.myFeet = this.myFeet - m2.myFeet;
		newMeasurement.myInches = this.myInches - m2.myInches;
		while (newMeasurement.myInches < 0) {
			if (newMeasurement.myFeet > 0) {
			newMeasurement.myFeet = newMeasurement.myFeet - 1;
			newMeasurement.myInches = newMeasurement.myInches + 12;
			}
			else {
				System.out.print ("It cannot substract the value");
			}
		}
		return newMeasurement;
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
			Measurement newMeasurement = new Measurement();
			newMeasurement.myFeet = this.myFeet * multipleAmount;
			newMeasurement.myInches = this.myInches * multipleAmount;
			
			while (newMeasurement.myInches >= 12) {
				newMeasurement.myFeet = newMeasurement.myFeet + 1;
				newMeasurement.myInches = newMeasurement.myInches - 12;
			}
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
		newString = Integer.toString(this.myFeet) + " ' " + Integer.toString(this.myInches) + " '' ";  
		return newString; 
	}

}

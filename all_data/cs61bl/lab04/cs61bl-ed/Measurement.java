public class Measurement {
	
	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	private int mes_feet;
	private int mes_inches;
	public Measurement() {
		mes_feet = 0;
		mes_inches = 0;

	}

	/**
	 * Constructor: takes a number of feet as its single argument, using 0 as
	 * the number of inches
	 */
	public Measurement(int feet) {
		mes_feet = feet;
		mes_inches = 0;
	}

	/**
	 * Constructor: takes the number of feet in the measurement and the number
	 * of inches as arguments (in that order), and does the appropriate
	 * initialization
	 */
	public Measurement(int feet, int inches) {
		mes_feet = feet;
		mes_inches = inches;

	}

	/**
	 * Returns the number of feet in in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 1.
	 */
	public int getFeet() {
		return mes_feet; // provided to allow the file to compile
	}

	/**
	 * Returns the number of inches in this Measurement. For example, if the
	 * Measurement has 1 foot and 6 inches, this method should return 6.
	 */
	public int getInches() {
		return mes_inches; // provided to allow the file to compile
	}

	/** Adds the argument m2 to the current measurement */
	public Measurement plus(Measurement m2) {
		int this_feet = this.mes_feet;
		int other_feet = m2.mes_feet;
		int this_inches  = this.mes_inches;
		int other_inches = m2.mes_inches;
		
		return new Measurement(this_feet+other_feet+(this_inches+other_inches)/12, (this_inches+other_inches)%12); // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int this_feet = this.mes_feet;
		int other_feet = m2.mes_feet;
		int this_inches  = this.mes_inches;
		int other_inches = m2.mes_inches;
		int result_feet = this_feet-other_feet;
		int result_inches = this_inches-other_inches;
		if(result_inches < 0){
			result_inches = 12+ result_inches;
			result_feet -= 1;
			
		}
		return new Measurement(result_feet, result_inches); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int this_feet = this.mes_feet;
		int this_inches  = this.mes_inches;
		int total_inches = (this_feet*12 + this_inches)*multipleAmount;
		
		return new Measurement(total_inches/12,total_inches%12); // provided to allow the file to compile
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return new String(mes_feet + "\'" + mes_inches + "\""); // provided to allow the file to compile
	}


}

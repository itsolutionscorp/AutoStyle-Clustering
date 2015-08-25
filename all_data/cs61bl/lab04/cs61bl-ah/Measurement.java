public class Measurement {

	/**
	 * Constructor: initialize this object to be a measurement of 0 feet, 0
	 * inches
	 */
	int feet;
	int inches;
	
	
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
		int f = 0;
		int i = 0;
		i = (inches+m2.getInches())%12;
		f = feet+m2.getFeet()+(inches + m2.getInches())/12;
		return new Measurement(f,i); // provided to allow the file to compile
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		int f = 0;
		int i = 0;
		i = (inches-m2.getInches())%12;
		f = feet-m2.getFeet()-(m2.getInches()/12);
		if (i<0){
			i += 12;
			f -= 1;
		}
		return new Measurement(f,i); // provided to allow the file to compile
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		int f = 0;
		int i = 0;
		i = (inches * multipleAmount)%12;
		f = (feet * multipleAmount) + (inches * multipleAmount)/12;	
		return new Measurement(f,i); // provided to allow the file to compile
		
		
		
		
		
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		
		return ""+feet+"\'"+inches+"\""; // provided to allow the file to compile
		
	}

}

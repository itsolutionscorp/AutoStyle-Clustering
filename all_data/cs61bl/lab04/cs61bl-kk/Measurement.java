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
		
		while (inches >= 12) {
			inches = inches - 12;
			feet = feet + 1;
		}
		
		this.feet = feet;
		this.inches = inches;

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
		Measurement answer = new Measurement(this.getFeet() + m2.getFeet(), this.getInches() + m2.getInches());
		return answer; 
	}

	/**
	 * Subtracts the argument m2 from the current measurement. You may assume
	 * that m2 will always be smaller than the current measurement.
	 */
	public Measurement minus(Measurement m2) {
		Measurement answer = new Measurement(this.getFeet(), this.getInches());
		answer.inches = answer.getInches() - m2.getInches();
		while (answer.inches <= 0) {
			answer.feet = answer.getFeet() - 1;
			answer.inches = answer.getInches() + 12;
		answer.feet = answer.getFeet() - m2.getFeet();
		}
		Measurement realAnswer = new Measurement(answer.getFeet(), answer.getInches());
		return realAnswer;
		
	}

	/**
	 * Takes a nonnegative integer argument n, and returns a new object that
	 * represents the result of multiplying this object's measurement by n. For
	 * example, if this object represents a measurement of 7 inches, multiple
	 * (3) should return an object that represents 1 foot, 9 inches.
	 */
	public Measurement multiple(int multipleAmount) {
		Measurement answer = new Measurement(this.getFeet() * multipleAmount, this.getInches() * multipleAmount);
		return answer;
	}

	/**
	 * toString should return the String representation of this object in the
	 * form f'i" that is, a number of feet followed by a single quote followed
	 * by a number of inches less than 12 followed by a double quote (with no
	 * blanks).
	 */
	@Override
	public String toString() {
		return new String(); // provided to allow the file to compile
	}
}

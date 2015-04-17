import list.EquationList;

public class Calculator {
	// YOU MAY WISH TO ADD SOME FIELDS
	private EquationList history;
	

	/**
	 * TASK 2: ADDING WITH BIT OPERATIONS
	 * add() is a method which computes the sum of two integers x and y using 
	 * only bitwise operators.
	 * @param x is an integer which is one of two addends
	 * @param y is an integer which is the other of the two addends
	 * @return the sum of x and y
	 **/
	public int add(int x, int y) {
		int carryOver = 0;
		int value = 0;
		int sum = 0;

		for (int i = 0; i < 32; i++){
			value = carryOver ^ getBit(x, i) ^ getBit(y,i);
			
			if (value == 1){
				sum = setBit(sum, i);
			}

			if (getBit(x, i) == 1 && getBit(y,i) == 1
					|| getBit(x, i) == 1 && carryOver == 1
					|| getBit(y,i) == 1 && carryOver == 1){
				carryOver = 1;
			} else {
				carryOver = 0;
			}
		}

		return sum;
	}

	/** returns the ith bit from the right of x **/
	private int getBit(int x, int i){
		return (x >> i) & 1;
	}

	private int setBit(int x, int i){
		return x | (1 << i);
	}


	/**
	 * TASK 3: MULTIPLYING WITH BIT OPERATIONS
	 * multiply() is a method which computes the product of two integers x and 
	 * y using only bitwise operators.
	 * @param x is an integer which is one of the two numbers to multiply
	 * @param y is an integer which is the other of the two numbers to multiply
	 * @return the product of x and y
	 **/
	public int multiply(int x, int y) {
		int product = 0;
		int toAdd = 0;
		int bitY = 0; // Checking the ith bit of the y parameter
		
		for (int i = 0; i < 32; i++){
			bitY = getBit(y,i);
			if (bitY == 1){
				toAdd = x << i;
			} else {
				toAdd = 0;
			}			
			product = add(product, toAdd);
		}		
		return product;
	}

	/**
	 * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
	 * saveEquation() updates calculator history by storing the equation and 
	 * the corresponding result.
	 * Note: You only need to save equations, not other commands.  See spec for 
	 * details.
	 * @param equation is a String representation of the equation, ex. "1 + 2"
	 * @param result is an integer corresponding to the result of the equation
	 **/
	public void saveEquation(String equation, int result) {
		if (history == null) {
			history = new EquationList(equation, result, null);
			return;
		} 
		
		EquationList p = history;
		
		history = new EquationList(equation, result, p);
	}

	/**
	 * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
	 * printAllHistory() prints each equation (and its corresponding result), 
	 * most recent equation first with one equation per line.  Please print in 
	 * the following format:
	 * Ex   "1 + 2 = 3"
	 **/
	public void printAllHistory() {
		printHistory(Integer.MAX_VALUE);
	}

	/**
	 * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
	 * printHistory() prints each equation (and its corresponding result), 
	 * most recent equation first with one equation per line.  A maximum of n 
	 * equations should be printed out.  Please print in the following format:
	 * Ex   "1 + 2 = 3"
	 **/
	public void printHistory(int n) {
		if (history == null) {
			return;
		}
	
		EquationList p = history;
		while (p != null && n > 0) {
			System.out.println(p.equation + " = " + p.result);
			p = p.next;
			n -=1;
		}		
		
	}    

	/**
	 * TASK 6: CLEAR AND UNDO
	 * undoEquation() removes the most recent equation we saved to our history.
	 **/
	public void undoEquation() {
		EquationList p = history;
		history = p.next;
	}

	/**
	 * TASK 6: CLEAR AND UNDO
	 * clearHistory() removes all entries in our history.
	 **/
	public void clearHistory() {
		history = null;
	}

	/**
	 * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
	 * cumulativeSum() computes the sum over the result of each equation in our 
	 * history.
	 * @return the sum of all of the results in history
	 **/
	public int cumulativeSum() {
		int sum = 0;
		
		EquationList p = history;
		
		while(p!= null){
			sum += p.result;
			p = p.next;
		}
		return sum;
	}

	/**
	 * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
	 * cumulativeProduct() computes the product over the result of each equation 
	 * in history.
	 * @return the product of all of the results in history
	 **/
	public int cumulativeProduct() {
		int product = 1;
		
		EquationList p = history;
		
		while(p!= null){
			product *= p.result;
			p = p.next;
		}
		return product;
	}
}
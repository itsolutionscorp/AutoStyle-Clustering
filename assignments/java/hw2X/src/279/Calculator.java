import list.EquationList;

public class Calculator {
     int prevXorBit;
     int currXorBit;
     int prevCarryBit;
     int currCarryBit;  

     EquationList start = null;
     int historySize = 0;
	
     public static void main(String[] args) {
	Calculator tester = new Calculator();
     } 
	
     /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
	prevXorBit = x ^ y;
	prevCarryBit = (x & y) << 1;
	while (prevCarryBit != 0) {
		currXorBit = prevXorBit ^ prevCarryBit;
		currCarryBit = (prevXorBit & prevCarryBit) << 1;
		prevXorBit = currXorBit;
		prevCarryBit = currCarryBit;
    	}
        return prevXorBit;
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
	int result = 0;
	int shift = 0;
	if ((x == 0) || (y == 0)) {
		result = 0;
	}
	while (y != 0) {
		if ((y & 1) == 1) {
			result = add(result, x << shift);
		}
		y = y >>> 1;
		shift = shift + 1;
    	}
	return result;
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
	EquationList entry = new EquationList(equation, result, start);
	start = entry;
	historySize = historySize + 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	printHistory(historySize);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
	if (n > historySize) {
		n = historySize;
	}

	EquationList tmp = start;
	while (n > 0) {
		System.out.println(tmp.equation + " = " + tmp.result);
		tmp = tmp.next;
		n = n - 1;
	}	
    }
    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
	if (start != null) {
		start = start.next;
	}
	if (historySize > 0) {
		historySize = historySize - 1;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        start = null;
	historySize = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList tmp = start;
	int result = 0;
	while (tmp != null) {
		result = result + tmp.result;
		tmp = tmp.next;
	}
	return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList tmp = start;
	int result = 1;
	while (tmp != null) {
		result = result * tmp.result;
		tmp = tmp.next;
	}
	return result;
    }
}

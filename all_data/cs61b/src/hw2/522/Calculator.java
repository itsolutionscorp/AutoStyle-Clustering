import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public EquationList eqList = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
	public int add(int x, int y)
	{   // base case is when there are no bits to carry   
	    if ((x & y) == 0)	// "&" bitwise AND operation
	    	return x | y;	// "|" bitwise inclusive OR operation
	    else				// add non-carried bits to carried ones shifted left
	    	return add(x ^ y, (x & y) << 1);  	// "^" bitwise exclusive OR 
	}
	
	public int subtract(int x, int y) {
		return add(add(x, ~y), 1);
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
    	if (x == 0 || y == 0)	// if x or y are zero we are done
    		return 0;
    	else if ((x & 1) == 1)	// x's bottom bit is 1, so add y to the multiplication of
    		return add(y, multiply(x >> 1, y << 1)); // x shifted right and y shifted left
    	else
    		return multiply(x >> 1, y << 1);	// x's bottom bit is 0, so just multiply next bit(s)
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
        if (eqList == null)
        	eqList = new EquationList(equation, result, null);
        else
        	eqList = new EquationList(equation, result, eqList);	// most recent first
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
    	for (EquationList eqPtr = eqList; eqPtr != null && n > 0; eqPtr = eqPtr.next, n--) {
    		// %s is a string, %d decimal integer, %n a platform-independent newline
    		System.out.format("%s = %d%n", eqPtr.equation, eqPtr.result);
    	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	if (eqList != null)
    		eqList = eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	int sum = 0;
    	
    	for (EquationList eqPtr = eqList; eqPtr != null; eqPtr = eqPtr.next) {
    		sum = add(sum, eqPtr.result);
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
    	
    	for (EquationList eqPtr = eqList; eqPtr != null; eqPtr = eqPtr.next) {
    		product = multiply(product, eqPtr.result);
    	}
    	return product;
    }
}
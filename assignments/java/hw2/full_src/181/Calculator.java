import list.EquationList;

public class Calculator {
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
    	int z = 0;
    	int carry = 0;
    	
    	for(int i = 0; i < 32; i++) {
        	int bit1 = x & 0x1;
        	int bit2 = y & 0x1;
        	int val = bit1 ^ bit2 ^ carry;
        	z = z | (val << i);
        	carry = (bit1 & bit2) | (bit1 & carry) | (bit2 & carry);
        	x = x >> 1;
        	y = y >> 1;
    	}
        return z;
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
        int z = 0;
        
        for(int i = 0; i < 32; i++) {
        	int bit2 = y & 0x1;
        	
        	if(bit2 == 1) {
        		z = add(z, (x << i));
        	}
        	y = y >> 1;
        }
        return z;
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
    	if(history == null) {
    		history = new EquationList(equation, result, null);
    	}
    	else {
	    	EquationList lstCopy = new EquationList(history.equation, history.result, history.next);
	    	history.equation = equation;
	    	history.result = result;
	    	history.next = lstCopy;
    	}
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	EquationList iter = history;
    	while(iter != null) {
    		System.out.println(iter.equation + " = " + iter.result);
    		iter = iter.next; 
    	}
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	EquationList iter = history;
    	while(n > 1) {
    		if(iter == null)
    			return;
    		iter = iter.next;
    		n--;
    	}
    	
    	if(iter == null)
    		return;
    	System.out.println(iter.equation + " = " + iter.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(history != null)
        	history = history.next;
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
        int val = 0;
        EquationList iter = history;
        while(iter != null) {
        	val += iter.result;
        	iter = iter.next;
        }
        return val;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
    	int val = 1;
        EquationList iter = history;
        while(iter != null) {
        	val *= iter.result;
        	iter = iter.next;
        } 
        return val;
    }
}
import list.EquationList;

public class Calculator {
    EquationList history;
    int historyLength;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    	int zBit = 0;
    	int sum = 0;
    	
        for (int i = 0; i < 32; i++){
	    	int xBit = getBit(x, i);
	    	int yBit = getBit(y, i);
	    	sum = (xBit ^ yBit ^ zBit) << i | sum ;
	    	zBit = ((xBit & yBit) | (zBit & (xBit ^ yBit)));
        }
        return sum;
    }
    
    /*
     * Returns the kth bit of n. Code from Stack Overflow question #14145733.
     */
    public int getBit(int n, int k) {
    	return (n >> k) & 1;
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
        int sum = 0;
    	for(int i = 0; i < 32; i++) {
    		int yBit = getBit(y, i);
    		if (yBit == 1) {
    			sum = add(sum, x << i);
    		}
    	}
        return sum;
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
    		}
    	else {
    		history = new EquationList(equation, result, history);
    	}
    	historyLength += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	printHistory(historyLength);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	EquationList temp = history;
    	
    	if (n > historyLength) {
    		n = historyLength;
    	}
    	
        for (int i = 0; i < n; i ++) {
        	System.out.println(temp.equation + " = " + temp.result);
        	temp = temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
        historyLength--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        historyLength = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	if (history == null) {
    		return 0;
    	}
        EquationList temp = history;
        int sum = temp.result;
        temp = temp.next;
        for (int i = 1; i < historyLength; i++) {
        	sum = add(temp.result, sum);
        	temp = temp.next;
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
    	if (history == null) {
    		return 0;
    	}
        EquationList temp = history;
        int product = temp.result;
        temp = temp.next;
        for (int i = 1; i < historyLength; i++) {
        	product = multiply(temp.result, product);
        	temp = temp.next;
        }
        return product;
    }
}
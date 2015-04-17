import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
    private int hist_counter = 0; // To keep track of how many calculator commands have been entered.

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
	/* Strategy: Use XOR to compute sum between 1 and 0. Use & to find the carry ones.
	** Then shift the carry number (the result of x & y) by << 1 and assign this number to y.
	** Keep going until y is 0 (all carry terms would have been accounted for by then).
	*/

	int temp; // carry term	
	while (y != 0) {
	  temp = x & y;
	  x ^= y;
	  y = temp << 1;
	}
	return x;
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
	/* Strategy / Alogorithm:
	** Create a variable to hold the product (call it p)
	** If the last bit of the multiplier is a 1, then add the multiplicand to p,
	** left-shift the multiplicand, and right-shift the multiplyer.
	** If the last bit of the multiplyer is 0, add nothing to p, left-shift multiplicand,
	** and right shift multiplyer. 
	*/

	int p = 0; // variable to hold product. will ultimately return.
	while (y != 0) {
	    if ((y & 1) != 0) { 	// Checks if last bit of multiplyer is one or no
	        p = add(p,x);
	    }
	x = x << 1;
	y = y >>> 1;
	}
	return p;
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
	history = new EquationList(equation,result,history);
	hist_counter += 1;

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	printHistory(hist_counter);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
	if(n > hist_counter) {
	    n = hist_counter;
	}
	EquationList temp = history; // So I don't modify what history points to
	int i = 0;    
        while (i < n) {
	    System.out.println(temp.equation + ' ' + '=' + ' ' + temp.result);
	    temp = temp.next;
	    i += 1;
	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	history = history.next;
	hist_counter -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
    	history = null;
	hist_counter = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
	if (hist_counter == 0) {
	    return 0;
	}

	int cumSum = 0;
	int temp_counter = hist_counter;
	EquationList hist_helper = history;

	while (temp_counter > 0) {
	    cumSum += hist_helper.result;
	    hist_helper = hist_helper.next;
	    temp_counter -= 1;
	}

	return cumSum;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
	if (hist_counter == 0) {
	    return 1;
	}

	int cumProd = 1;
	int temp_counter = hist_counter;
	EquationList hist_helper = history;

	while (temp_counter > 0) {
	    cumProd *= hist_helper.result;
	    hist_helper = hist_helper.next;
	    temp_counter -= 1;
	}

	return cumProd;
    }
}

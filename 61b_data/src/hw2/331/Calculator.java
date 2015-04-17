import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        // Yay being a math major! But not really, lol
        // This actually took some thought about "carrying the 1's" in a clever way
       int both = x & y;
       int either = x | y;
       if ((either & both) == 0) {
          return (either | both);
       }
       return add(x ^ y, both << 1);
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
        // YOUR CODE HERE
        // This took some time cleaning up the solution I had before..
        // Note that bit-shifting to the left effectively multiplies by 2
        /* Also note: for example, let x = 10, y = 7
           7 = 1*2^2 + 1*2^1 + 1*2^0, so
           x * y = x*(1*2^2) + x*(1*2^1) + x*(1*2^0)
        */
        if (y == 0 || x == 0) { // Just in case both x and y are 0
        	return 0;
        }
    	int product = 0;
		if ((y & 1) == 1){ // Deal with right-most bit of y
        	product = x;
        }
        if (y == 1) { // If y is 1, we're done
        	return product;
        }
        return add(product, multiply(x<<1, y>>>1)); // Recursive call, with bit shifting to multiply/divide by 2
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
        // YOUR CODE HERE
        // Note: I named the history "history"
        history = new EquationList(equation, result, history); // Since history goes by most recent
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        int history_length = 0;
        if (history != null) {
        	history_length = 1;
        	EquationList tmp_next = history.next;
        	while (tmp_next != null) {
        		history_length += 1;
        		tmp_next = tmp_next.next;
        	}
        }
        printHistory(history_length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        if (history != null) {
			String tmp_eqn = history.equation;
        	int tmp_res = history.result;
        	EquationList tmp_next = history.next;
        	System.out.println(tmp_eqn + " = " + tmp_res);
        	int i = 1;
        	while ((i < n) && tmp_next != null) {
				tmp_eqn = tmp_next.equation;
        		tmp_res = tmp_next.result;
        		tmp_next = tmp_next.next;
        		System.out.println(tmp_eqn + " = " + tmp_res);
        		i += 1;
        	}
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history != null) {
        	history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (history == null) {
        	return 0;
        }
		int cum_sum = history.result;
        EquationList tmp_next = history.next;
        while (tmp_next != null) {
        	cum_sum = add(cum_sum, tmp_next.result);
        	tmp_next = tmp_next.next;
        }
        return cum_sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        if (history == null) {
        	return 1;
        }
		int cum_prod = history.result;
        EquationList tmp_next = history.next;
        while (tmp_next != null) {
        	cum_prod = multiply(cum_prod, tmp_next.result);
        	tmp_next = tmp_next.next;
        }
        return cum_prod;
    }

}
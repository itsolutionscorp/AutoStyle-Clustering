import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
	
	//Received help from office hours.
    public int add(int x, int y) {
    	int newand = x & y;
    	int carrot = x ^ y;
    	
    	while(newand != 0) {
    		int and = newand << 1;
    		newand = and & carrot;
    		carrot = carrot ^ and;
    	}
    	return carrot;
    }
   
    
    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    
    //Received help from office hours.
    public int multiply(int x, int y) {
    	int product = 0;
    	
        if (x == 0 || y == 0) {
            return 0;
        }
        
        else if (x == 1) {
            return y;
        }

        else if (y == 1) {
            return x;
        }
        
        while (y != 0)
        {
            if ((y & 1) != 0)
            {
                product = product + x;
            }
            x = x << 1;
            y = y >>> 1;
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
    		history.equation = equation;
    		history.result = result;
    	}
    	else {
    		history = new EquationList(equation, result, history);
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
        // YOUR CODE HERE
    	while (history != null) {
    		System.out.println(history.equation + " = " + history.result);
    		history = history.next;
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
        // YOUR CODE HERE
    	while (history != null && n > 0) {
    		System.out.println(history.equation + " = " + history.result);
    		history = history.next;
    		n -= 1;
    	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	history.equation = history.next.equation;
    	history.result = history.next.result;
    	history.next = history.next.next;
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
        int sum = 0;
        while (history != null) {
        	sum = sum + history.result;
        	history = history.next;
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
        while (history != null) {
        	product = product * history.result;
        	history = history.next;
        }
        return product;
    }
}
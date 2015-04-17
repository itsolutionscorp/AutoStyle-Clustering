import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList headSave = null;
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
    	
        int pure_nums, carry_over, temp;
        carry_over = x & y;
        pure_nums = x ^ y;

        while (carry_over != 0) {
        	carry_over = carry_over << 1; //Shift to the left (carry operation)
            temp = pure_nums ^ carry_over; //1 + 1 = 0, move those over and
            				//carries will go to and
            carry_over = carry_over & pure_nums; //Find everything that will be carried
            				//over and shift in next iteration
            pure_nums = temp;
        }
        return pure_nums;
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
        /** Run the code so that we stock up on value.
         * 	Only time we have odd values is at the beginning or end
         *  Meaning that we get the first X that we lose at the start
         *  Continue to do the math, then stop as soon as we've reached 1 or 0.
         */
    	if (y < 0 && x > 0) {
    		int temp = y; //Switch them if y is negative, x positive
    		y = x;
    		x = temp;
    	}
    	if (y < 0 && x < 0) {
    		y = -y; //Make both positive if both negative
    		x = -x;
    	}
    	int result=0;
    	while (y != 0)
	    {
	       if ((y & 01) != 0)
	       {
	    	   result = add(result, x);     // Add x if b is odd .
	       }
	       x<<=1;        // Double value of x
	       y>>=1;        // Half value of y
	       				// If odd then we lose 1 value.
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
    	EquationList tempSave = new EquationList(equation, result, headSave);
    	headSave = tempSave;
    	
//    	if ( == null) {
//    		firstSave = new EquationList(equation, result, null);
//    	} else {
//    		EquationList parser = firstSave;
//    		while (parser.next != null) {
//    			parser = parser.next;
//    		}
//    		parser.next = new EquationList(equation, result, null);
//    	}
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
    	printHistory(-1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     * If n == -1, then it will print all available history
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
    	if (headSave == null)
    		return;
    	EquationList parser = headSave;
    	while(n != 0) {
    		System.out.println(parser.equation + " = " + parser.result);
    		if (parser.next == null)
    			return;
    		parser = parser.next;
    		n--;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	if (headSave != null) {
    		headSave = headSave.next;
    	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
    	//printAllHistory();
    	headSave = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList parser = headSave;
        int sum = 0;
        if (parser == null)
        	return sum;
        while (parser != null) {
        	sum = add(sum, parser.result);
        	parser = parser.next;
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
        // YOUR CODE HERE
    	EquationList parser = headSave;
        int prod = 1;
        if (parser == null)
        	return prod;
        while (parser != null) {
        	prod = multiply(prod, parser.result);
        	parser = parser.next;
        }
        return prod;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    private EquationList history = null;

	private int getBit(int x, int i) {
		return (x >>> i) & 1;
	}

	private int setBit(int x, int i){
		return x | (1 << i);
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
        int sum = x ^ y; //takes the sum
        int carries = x & y; //find the carries
        int temp;

        while (carries != 0) { //checks if there is any carries
            carries <<= 1; //moves the carries left by one
            temp = sum ^ carries; //retakes the new sum
            carries &= sum; //refinds the new carries
            sum = temp; //replaces with new sum
        }
        return sum; //returns final sum when there are no more carries
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
        int sum = 0; //keeps track of sum
        while (y != 0) { //checks to see if any places are left over
        	if ((y & 1) == 1) { //checks if there is a 1 in place
        		sum = add(sum, x); //adds x * 2^n
        	}
        	y >>>= 1; //moves y right 1
        	x <<= 1; //moves x left 1
        }
        return sum; //returns sum when y is 0
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
        history = new EquationList(equation, result, history);
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
        int i = 1;
        while (history != null){
        	printHistory(i);
        	i += 1;
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
        if (history != null) {
	    	EquationList d_history = history;
	        while (n > 1) {
	        	d_history = history.next;
	        	n -= 1;
	        }
	        System.out.println(d_history.equation + " = " + d_history.result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
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
        EquationList d_history = history;
        while (d_history != null) {
        	sum += d_history.result;
        	d_history = history.next;
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
        int product = 1;
        EquationList d_history = history;
        while (d_history != null) {
        	product *= d_history.result;
        	d_history = history.next;
        }
        return product;
    }
}
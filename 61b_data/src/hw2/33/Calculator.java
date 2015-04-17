import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList recent = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        //x is the send back. y is the combiner.
	int temp;
	while (x != 0) {
	    temp = x & y;
	    x = x & ~temp;
	    y = y & ~temp;
	    temp = temp << 1;
	    y = y ^ x;
	    x = temp;
	    }
	return y;
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
        //x will get multiplied by y; sent to a new temp;
	int temp = 0;

	//clear 0 multiplication;
	if (y == 0 || x == 0)
		return 0;
	
	//get signs set
	boolean neg = false;
	if (y < 0) {
	     neg = !neg;
	     y = add(~y,1);
	}
	if (x < 0) {
	     neg = !neg;
	     x = add(~x,1);
	}

	while (y > 0) {
	    if ((y & 1) == 1) {
            	temp = add(temp,x);
	    }
	    x = x << 1;
	    y = y >>> 1;
	}
	
	if (neg) {
	   temp = add(~temp,1);
	}

	return temp;
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
        this.recent = new EquationList(equation, result, this.recent);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(-1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	//if n is negative, it will output the entire list.
        EquationList pointer = this.recent;
	while(n != 0 && pointer != null) {
	    System.out.println(pointer.equation + " = " + pointer.result);
	    n -= 1;
	    pointer = pointer.next;
	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        this.recent = this.recent.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.recent = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
	int val = 0;
	EquationList pointer = this.recent;
	while (pointer != null) {
	    val = add(val, pointer.result);
	    pointer = pointer.next;
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
	EquationList pointer = this.recent;
	while (pointer != null) {
	    val = multiply(val,pointer.result);
	    pointer = pointer.next;
	}
	return val;
    }
}

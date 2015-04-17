import list.EquationList;

public class Calculator {
    public EquationList history = new EquationList("", 1, null); 

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int addWithoutCarry = x ^ y;
	int carryHolder = (x & y) << 1;
        if (carryHolder != 0) {
	    return add(addWithoutCarry, carryHolder);
	}
	return addWithoutCarry;
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
	if (y == 0) {
	    return 0;
	}
	else if ((y & 1) == 1) {
	    return add(x, multiply(x << 1, y >> 1));
	}
	return multiply(x << 1, y >>> 1);
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
	EquationList toAdd = new EquationList(equation, result, null);
	toAdd.next = history;
	history = toAdd;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	EquationList nonDestruct = new EquationList(history.equation, history.result, history.next);
        while (nonDestruct.next != null) {
	    System.out.println(nonDestruct.equation + " = " + nonDestruct.result);
	    nonDestruct = nonDestruct.next;
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
	EquationList nonDestruct = new EquationList(history.equation, history.result, history.next);
        int count = 0;
	while (count < n && nonDestruct.next != null) {
	    System.out.println(nonDestruct.equation + " = " + nonDestruct.result);
	    nonDestruct = nonDestruct.next;
	    count++;
	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList("", 1, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
	EquationList nonDestruct = new EquationList(history.equation, history.result, history.next);
	while (nonDestruct.next != null) {
	    sum += nonDestruct.result;
	    nonDestruct = nonDestruct.next;
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
	EquationList nonDestruct = new EquationList(history.equation, history.result, history.next);
	while (nonDestruct.next != null) {
	    product *= nonDestruct.result;
	    nonDestruct = nonDestruct.next;
	}
        return product;
    }
}

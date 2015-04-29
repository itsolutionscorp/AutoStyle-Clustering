import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public EquationList history;

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
		if (y  == 0) {
			return x;
		}
		else {
			return add(x^y, (x & y) << 1);
		}
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
		if (x < 0 && y > 0) {
			return -multiply(-x, y);
		}
		if (x > 0 && y < 0) {
			return -multiply(x, -y);
		}
		if (x < 0 && y < 0) {
			return multiply(-x, -y);
		}
		int product = 0;
		while (y != 0) {
			if ((y & 01) != 0) {
				product = add(product, x);
			}
			x <<=1;
			y >>=1;
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
		} else {
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
        EquationList ref = history;
		while (ref != null) {
			System.out.println(ref.equation + " = " + ref.result);
			ref = ref.next;
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
        EquationList ref = history;
		for(int i = n; i > 0; i--) {
			System.out.println(ref.equation + " = " + ref.result);
			ref = ref.next;
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
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = history.result;
		EquationList ref = history;
		while (ref.next != null) {
			ref = ref.next;
			sum = add(sum, ref.result);
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
        int prod = history.result;
		EquationList ref = history;
		while (ref.next != null) {
			ref = ref.next;
			prod = multiply(prod, ref.result);
		}
        return prod;
    }
}
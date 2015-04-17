import list.EquationList;

public class Calculator {
    EquationList history = new EquationList(null, 0, null);
    int N = 0;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int two_ones = 0;
	int result = 0;
	int x_bit, y_bit, total;
	for (int i = 0; i < 32; i++) {
	    x_bit = (x & (1 << i)) >>> i;
	    y_bit = (y & (1 << i)) >>> i;
	    total = x_bit ^ y_bit ^ two_ones;
	    if (two_ones == 0) {
		two_ones = x_bit & y_bit;
	    } else {
		two_ones = x_bit | y_bit;
	    }
	    result = (total << i) | result;
	}
	return result;
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
        int result = 0;
	while (y != 0) {
	    if ((y & 1) == 1) result = add(result, x);
	    x <<= 1;
	    y >>>= 1;
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
	EquationList copy = history;
	if (copy.equation == null) {
	    copy.equation = equation;
	    copy.result = result;
	    N = 1;
	} else {
	    while (copy.next != null) {
		copy = copy.next;
	    }
	    copy.next = new EquationList(equation, result, null);
	    N += 1;
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
	printHistory(N);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        String[] lines = new String[n];
	EquationList copy = history;
	if (copy.equation == null) {
	    return;
	} else if (n <= N){
	    for (int i = 0; i < (N - n); i++) copy = copy.next;
	    for (int i = 0; i < n; i++) {
		lines[n - i - 1] = copy.equation + " = " + copy.result;
		copy = copy.next;
	    }
	    for (String line: lines) System.out.println(line);
	} else {
	    printHistory(N);
	}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
	EquationList copy = history;
	if (N == 1) {
	    history = new EquationList(null, 0, null);
	} else {
	    for(int i = 0; i < (N - 2); i++) copy = copy.next;
	    copy.next = null;
	}
	N -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList(null, 0, null);
	N = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/

    public int cumulativeSum() {
	EquationList copy = history;
	int sum = 0;
        for (int i = 0; i < N; i++) {
	    sum += copy.result;
	    copy = copy.next;
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
	EquationList copy = history;
	int product = 1;
        for (int i = 0; i < N; i++) {
	    product *= copy.result;
	    copy = copy.next;
	}
	return product;
    }
}

import list.EquationList;

public class Calculator {

    private static final int negTest = (1 << 31);
    public EquationList history;
    private int historyLen = 0;

    private boolean bothNegative(int x, int y) {
	return (isNegative(x) && isNegative(y));
    }

    private boolean isNegative(int x) {
	return ((x & negTest) != 0);
    }

    private int flipSign(int x) {
	return (add((~x),  1));
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
	int carry, digit1, digit2, digitloc, xorRez, sum;
	carry =  digit1 = digit2 = xorRez = sum = 0;
	digitloc = 1;
	if (bothNegative(x, y)) {
	    return flipSign(add(flipSign(x), flipSign(y)));
	}
	for (int incr = 1; incr < 33; incr += 1) {
	    digit1 = (digitloc & x);
	    digit2 = (digitloc & y);
	    xorRez = ((digit1 ^ digit2) ^ carry);
	    carry = (((digit1 & digit2) | (digit1 & carry) | (digit2 & carry)) << 1);
	    sum |= xorRez;
	    digitloc <<= 1;
	}
	return sum;
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
	if ((x == 0) || (y == 0)) {
	    return 0;
	}
	if ((y == 2) || (y == 1)){
	    return x << add(y, - 1);
	}
	if (isNegative(y)) {
	    return multiply(flipSign(x), flipSign(y));
	}
	if ((y & 1) == 0) {
	    return multiply((x << 1), (y >>> 1));
	}
	return add(x,  multiply(x, add(y, - 1)));
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
	EquationList cur = new EquationList(equation, result, null);
	EquationList cur2 = history;
	history = cur;
	cur.next = cur2;
	historyLen += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	printHistory(historyLen);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) { //rewrite later to use one above
	EquationList hold = history;
	while ((n > 0) && (hold != null)) {
	    System.out.println(hold.equation + " = " + String.valueOf(hold.result));
	    hold = hold.next;
	    n -= 1;
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
	int sum = 0;
	EquationList cur = history;
	while (cur != null) {
	    sum = add(sum, cur.result);
	    cur = cur.next;
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
        EquationList cur = history;
	while (cur != null) {
	    product = multiply(product, cur.result);
	    cur = cur.next;
	}
        return product;
    }
}

import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/


    EquationList history;
    int length = 0;

    public int add(int x, int y) {
        // YOUR CODE HERE
	int curBit = 1;
	int carry = 0;
	int yesBit = 0;
	while ((x != 0) || (carry != 0)) {
	        yesBit = curBit & x;
		if ((yesBit ^ carry) == curBit) {
			carry = (((x & carry) | (y & carry)) | ((y & curBit) & x));
			y = y ^ curBit;	}
		carry = carry<<1;
		curBit = curBit<<1;
		if ((yesBit & x) != 0) {
			x = x ^ yesBit; }
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
        // YOUR CODE HERE
        int total = 0;
	int curBit = 1;
	int yesBit = 0;
	while (x != 0) {
		yesBit = curBit & y;
		if (yesBit != 0) {
			total = add(total, x); }
		curBit = curBit << 1;
		x = x << 1;
		}
	return total;
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
	length += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
	printHistory(length);
        // YOUR CODE HERE
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
	EquationList cur = history;
	while ((cur != null) && (n != 0)) {
		System.out.print(cur.equation + " = ");
		System.out.println(cur.result);
		cur = cur.next;
		n = n -1; }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
	if (history != null) {
		history = history.next;
		length = length - 1;}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
	history = null;
	length = 0;
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
		cur = cur.next; }
        // YOUR CODE HERE
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
		cur = cur.next; }
	return product;
        // YOUR CODE HERE
    }
}

import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList lastEquation = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    	int carry = 0, sum = 0; // carry over, total sum
        for (int mask = 1; mask != 0; mask <<= 1, carry <<= 1) { // loop until mask = 0 i.e. when mask << out of bounds for Integer
        	int a = x & mask, b = y & mask; // select only current bit to calculate sum
        	sum |= a ^ b ^ carry; // xor for adding bits, assign with or to sum
        	carry = (a & b) | (a ^ b) & carry; // calculate carry over using AND
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
        int sum = 0; // total sum
        for (int mask = 1; mask != 0; mask <<= 1, x <<= 1) // loop until mask = 0 i.e. when mask << out of bounds for Integer
        	if ((y & mask) != 0) // only add select bits of y
        		sum = this.add(sum, x); // add x shifted appropriately
        return sum;
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
        lastEquation = new EquationList(equation, result, lastEquation);
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
    	EquationList currentEquation = lastEquation;
        while(n-- != 0) {
        	if(currentEquation == null)
        		return;
        	System.out.println(currentEquation.equation + " = " + currentEquation.result);
        	currentEquation = currentEquation.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(lastEquation != null)
        	lastEquation = lastEquation.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(lastEquation != null)
            lastEquation = lastEquation.next;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	int sum = 0;
    	EquationList currentEquation = lastEquation;
        while(currentEquation != null) {
        	sum += currentEquation.result;
        	currentEquation = currentEquation.next;
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
    	EquationList currentEquation = lastEquation;
        while(currentEquation != null) {
        	product *= currentEquation.result;
        	currentEquation = currentEquation.next;
        }
        return product;
    }
}
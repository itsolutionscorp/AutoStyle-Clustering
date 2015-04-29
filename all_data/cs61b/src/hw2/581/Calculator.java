import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public EquationList history = null;
	public int memory = 0;
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
        int carry = x & y;
	int sum = x ^ y;
	int shftd_carry = 0;
	while(carry != 0 ){
		shftd_carry = carry << 1;
		carry = sum & shftd_carry;
		sum = sum ^ shftd_carry;
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
        // YOUR CODE HERE
        int product = 0;
	if ((x == 0) || (y == 0)){
		return 0;
	}
	else if((x != 1)&&((x & (x - 1)) == 0)){
		x = x >> 1;
		while(x > 0){
			y = y << 1;
			x = x >> 1;
		}
		return y;
	}
	else if((y != 1)&&((y & (y - 1)) == 0)){
		y = y >> 1;
		while(y > 0){
			x = x << 1;
			y = y >> 1;
		}
		return x;
	}
	else{
		while(y > 0){
			product = add(product,x);
			y = add(y,-1);
		}
		return product;
	}
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
        if (history == null){
		history = new EquationList(equation,result,null);
		memory = memory + 1;
	}
	else{
		EquationList ptr = new EquationList(equation,result,history);
		history = ptr;
		memory = memory + 1;
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
        printHistory(memory);
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
        EquationList ptr = history;
        while(n > 0){
		System.out.println(ptr.equation + " = " + ptr.result);
		n = n - 1;
		ptr = ptr.next;
	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
	memory = memory - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
	memory = 0;
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
	EquationList ptr = history;
	while(ptr != null){
		sum = add(sum,ptr.result);
		ptr = ptr.next;
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
	EquationList ptr = history;
	while(ptr != null){
		product = multiply(product,ptr.result);
		ptr = ptr.next;
	}
        return product;
    }
}

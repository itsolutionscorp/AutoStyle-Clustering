import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	
	//Task 5A instance variable//
	public EquationList storageList = new EquationList("0",0, null);
	
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
        	if ((x&y) == 0){ 
        		return x ^ y;
        	}
        		int temp = x & y;
        		int temp2 = x ^ y;
        		temp = temp << 1;
        		return add(temp, temp2);
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
        	if (y == 0){
        		return 0; 
        	}
        	if (y == 1){
        		return x;
        	}
        	if (y == -1){
        		return add((~x),1);
        	}
        	if ((y^1) != add(y,1)){
        		return add(x, multiply(x,(y^1)));
        	}
        	x = x << 1;
        	y = y >> 1;
        	return multiply(x,y);
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
    	storageList = new EquationList(equation, result, storageList);
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
    	EquationList new_list = storageList;
    	while (new_list.next != null){
        	System.out.println(new_list.equation + " = " + Integer.toString(new_list.result));
        	new_list = new_list.next;
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
    	int counter = 0;
    	EquationList x = storageList;
    	while (counter < n && x.next != null){																													
        	System.out.println(x.equation + " = " + Integer.toString(x.result));
        	x = x.next;
        	counter += 1;	
    }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
    	storageList = new EquationList(storageList.next.equation, storageList.next.result, storageList.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
    	storageList = new EquationList("0", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
    	EquationList coolList = storageList;
    	int sum = 0;
    	while (coolList.next != null){
    		sum = add(sum, coolList.result);
    		coolList = coolList.next;
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
    	EquationList x = storageList;
    	int product = 1;
    	while (x.next != null){
    		product = multiply(product, x.result);
    		x = x.next;
    	}
        return product;
    }
}
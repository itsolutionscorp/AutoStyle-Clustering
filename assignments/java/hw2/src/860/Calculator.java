import list.EquationList;
// adding comments to see if commit if working

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	public EquationList equation;
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
    	// initialize to so while loop works
    	int carry = x;
    	int sum = y;
    	int temp = 0;
    	while (carry != 0){ 
        // get array of where carried 1's are located
    		temp = (carry & sum);
        // find the sum ignoring carries
    		sum = (carry ^ sum);
    	// shift over so carry is in the proper position
    		carry = temp << 1;	
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
    	Calculator calc = new Calculator();
    	int product = 0;
    	int flag = 0;
    	// fix y for counter
    	if (y < 0){
    		y = ~y + 1;
    		flag = 1;
    	}
        for (int k = 0; k<y; k++){
        	product = calc.add(product,x);
        }
        // account for negative y
        if (flag == 1){
        	product = ~product + 1;
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
        EquationList eq = new EquationList(equation, result, null);
        eq.next = this.equation;
        this.equation = eq;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    	String padded = String.format("%-1s", "= ");
    	EquationList tmp = this.equation;
        while (tmp != null){
        	System.out.println(tmp.equation + padded + tmp.result);
        	tmp = tmp.next;
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
    	String padded = String.format("%-1s", "= ");
    	EquationList tmp = this.equation;
    	for (int k = 0; k<n;k++){
    		System.out.println(tmp.equation + padded +  tmp.result);
    		tmp = tmp.next;
    	}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
    	EquationList tmp = this.equation;
        while (tmp.next.next != null){
        	tmp = tmp.next;
        }
        tmp.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.equation = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
    	Calculator calc = new Calculator();
    	int sum = 0;
    	EquationList tmp = this.equation;
    	while (tmp != null){
    		sum = calc.add(tmp.result,sum);
    		tmp = tmp.next;
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
    	Calculator calc = new Calculator();
    	int sum = 0;
    	EquationList tmp = this.equation;
    	while (tmp != null){
    		sum = calc.multiply(tmp.result,sum);
    		tmp = tmp.next;
    	}
    	return sum;
    }
}
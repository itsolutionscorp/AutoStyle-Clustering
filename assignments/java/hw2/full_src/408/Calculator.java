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
    private EquationList l = new EquationList("init", 0, null);
    private EquationList recent_saved;
  
    public int add(int x, int y) {
        int state = x & y;
        int result = x ^ y;
        while (state != 0) {
        	int new_state = state << 1;
        	state = result & new_state;
        	result = result ^ new_state; //for one time it settles one adding (1+1)
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
    	/** 
    	*int total = 0;
    	*while (y != 0) {
    	*	total = add(total, x);
    	*	y = add(y,-1);
    	*}
        *return total;
     	*
     	*Well I know this might not be that elegant. 
     	*/
     	int total = 0;
     	while (y != 0) {
     		if ((y & 1) == 1) {
     			total = add(total, x);
     		}
     		x = x << 1;
     		y = y >> 1;    			
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
    	l = new EquationList(equation, result, l);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = l;
        int n = 0;
        while (pointer != null)
        {
        	n++;
        	pointer = pointer.next;
        }
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
    	EquationList pointer = l;
        while (n > 0 && pointer.next != null) {
        	System.out.println(pointer.equation + " = " + pointer.result);
        	pointer = pointer.next;
        	n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        l = l.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        l.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = l;
        int sum = 0;
        while (pointer.next != null) {
        	sum += pointer.result;
        	pointer = pointer.next;
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
        EquationList pointer = l;
        int product = 1;
        while (pointer.next != null) {
        	product *= pointer.result;
        	pointer = pointer.next;
        }
        return product;
    }
}
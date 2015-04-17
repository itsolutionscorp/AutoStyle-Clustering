import list.EquationList;

public class Calculator {
    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int andResult = x & y;
        int xorResult = x ^ y;
        if (andResult == 0){
        	return xorResult;
        }
        else{
        	andResult = andResult << 1;
        	return add(xorResult, andResult);
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
        int i = 0;
        int k = 0;
        int z = 0;
        if (x < 0){
        	k = add(k, 1);
        	x = ~x;
        	x = add(x, 1);
        }
        if (y < 0){
        	k = add(k, 1);
        	y = ~y;
        	y = add(y, 1);
        }
        while (y != 0){
        if ((y & 1) == 0){
        	y = y >> 1;
        	i = add(i, 1);
        }
        else{
        	z = add(x << i, z);
        	y  = y >> 1;
        	i = add(i, 1);
        }
    }
    if ((k % 2) == 0){
    	return z;
    }
    else{
    	z = ~z;
    	z = add(z, 1);
    	return z;
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
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = history;
        while (pointer != null){
        	System.out.print(pointer.equation);
        	System.out.print(" = ");
        	System.out.println(pointer.result);
        	pointer = pointer.next;
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
        EquationList pointer2 = history;
        while ((pointer2 != null) && (n > 0)){
        	System.out.print(pointer2.equation);
        	System.out.print(" = ");
        	System.out.println(pointer2.result);
        	pointer2 = pointer2.next;
        	n--;
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
        int i = 0;
        EquationList pointer = history;
        while (pointer != null){
        	i += pointer.result;
        	pointer = pointer.next;
        }
        return i;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int j = 1;
        EquationList pointer = history;
        while (pointer != null){
        	j *= pointer.result;
        	pointer = pointer.next;
        }
        return j;
    }
}
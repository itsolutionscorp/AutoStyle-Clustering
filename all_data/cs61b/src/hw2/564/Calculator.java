import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
	EquationList history = null;

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
        /*
        RESOURCES USED: http://en.wikipedia.org/wiki/Adder_(electronics)
		Allowed to better grasp concept of adding (and carry-overs), used only for concepts, not for code directly

        	101
        	011
        	----
        and 001
		xor 110

		and << 1

		and 010
		xor 110

		xor ^ and
		
		
		xor capture 1+0=1 operations
		and captures carry operations 

		in order to carry use 2 * (x & y) + (x ^ y)


        */
        int and = x & y; //represents carry-overs
        int xor = x ^ y;
        int or = x | y;
        int xxor;

        /*for(int i = 0; i < 32; i++)
        {
        	and = and << 1; 
        	and = xor & and; 
        	xor = xor ^ and; 
        }*/

        while(and != 0) //keep looping as long as there
        {
        	and = and << 1; //shift carry-over to the next digit
        	xxor = xor ^ and;
        	and = xor & and; //if "xor" is 1 and "and" is 1 keep "and" as 1 to shift again 
        	xor = xxor; //perform addition 
        }

        return xor;
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
        boolean isNegative = false;

        if(y < 0)
        {
        	y = ~y + 1;
        	isNegative = true;
        }


        for (int i = 0; i < y; i++)
        {
        	total = add(x, total);
        }

        if(isNegative == true)
        	return ~total + 1;
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
        // YOUR CODE HERE
        EquationList temp = history;
        while(temp != null)
        {
        	System.out.println(temp.equation + " = " + temp.result);
        	temp = temp.next;
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
        EquationList temp = history;
        int count = 0;
        while(temp != null && count < n)
        {
        	System.out.println(temp.equation + " = " + temp.result);
        	temp = temp.next;
        	count++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if(history.next != null)
        	history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList temp = history;
        int total = 0;
        while(temp != null)
        {
        	total += temp.result;
        	temp = temp.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList temp = history;
        int total = 1;
        while(temp != null)
        {
        	total *= temp.result;
        	temp = temp.next;
        }
        return total;
    }
}
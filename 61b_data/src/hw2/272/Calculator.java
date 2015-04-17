import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
    public int historySize;

    public Calculator() {
    	history = null;
        historySize = 0;
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
        int toReturn = x ^ y;
        int toCarry = x & y;
        int shifted; //carried over one spot to the left

        while (toCarry != 0) {
            shifted = toCarry << 1;
            toCarry = toReturn & shifted;
            toReturn = toReturn ^ shifted;
        }
        return toReturn;
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
        int product = firstBit(x, y);
        int toAdd;
        int shifter = 1;
        
        while (y != 0) {
        	y = y >>> 1;
            toAdd = (firstBit(x, y)) << shifter;
            product = add(product, toAdd);
            shifter += 1;
        }
        return product;
    }

    /** 
     *  helper function for multiply, determines if the rightmost is 1 or 0.
     *  returns x or 0, if 1 or 0, respectively
     **/
    public int firstBit(int x, int y) {
    	int firstBit = y & 1;
    	if (firstBit != 0) {
        	return x;
        } else {
            return 0;
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
        historySize += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historySize);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList a = history;
        for (int i = 0; i < n; i += 1) {
            System.out.println(a.equation + " = " + a.result);
            a = a.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
        }
        historySize -= 1;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (history != null){
            history = history.next;
        }
        historySize = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList a = history;
        int sum = 0;
        while (a != null) {
            sum = add(sum, a.result);
            a = a.next;
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
        EquationList a = history;
        int product = a.result;
        while (a.next != null) {
            a = a.next;
            product = multiply(product, a.result);
        }
        return product;
    }
}
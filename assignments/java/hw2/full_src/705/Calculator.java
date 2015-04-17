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
    public int add(int x, int y) {
        // YOUR CODE HERE
        if (x < 0 && y > 0) {
            int temp = x;
            x = y;
            y = temp;
        }
        if (x == 0)
            return y;
        if (y == 0)
            return x;
        int curBit = 1;
        int counter = 0;
        if (Math.abs(x) > Math.abs(y))
            counter = x<<1;
        else
            counter = y<<1;
        boolean carry = false;
        while (counter != 0) {
            if ((y & curBit) == curBit) {
                if ((x & curBit) != curBit) {
                    if (!carry)
                        x = x|curBit;
                }
                else {
                    if (!carry) {
                        int flipCurBit = ~curBit;
                        x = x&flipCurBit;
                    }
                    carry = true;
                }
            }
            else if (carry && (x & curBit) == curBit) {
                int flipCurBit = ~curBit;
                x = x&flipCurBit;
            }
            else if (carry) {
                x = x|curBit;
                carry = false;
            }
            curBit = curBit<<1;
            counter = counter>>>1;
        }
        return x;
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
        if (x == 0 || y == 0)
            return 0;
        if (x < 0 && y > 0) {
            int temp = x;
            x = y;
            y = temp;
        }
        int rv = 0;
        for(int i = 0; i < Math.abs(y); i++) {
            rv = add(rv, x);
        }
        if (x < 0 && y < 0)
            return Math.abs(rv);
        else if(y < 0){
            return ~add(rv, -1);
        }
        else
            return rv;
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
    private EquationList history = null;
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (history == null)
            history = new EquationList(equation, result, null);
        else
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
        printHistory(Integer.MAX_VALUE);
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
        EquationList histCopy = history;
        for (int i = 0; i < n; i++) {
            if (histCopy == null)
                return;
            else {
                System.out.println(histCopy.equation + " = " + histCopy.result);
                histCopy = histCopy.next;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
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
        int rv = 0;
        while (temp != null) {
            rv += temp.result;
            temp = temp.next;
        }
        return rv;
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
        if (temp == null)
            return 0;
        int rv = temp.result;
        temp = temp.next;
        while (temp != null) {
            rv *= temp.result;
            temp = temp.next;
        }   
        return rv; 
    }
}
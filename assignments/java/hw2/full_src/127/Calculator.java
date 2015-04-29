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
    boolean tacocat = true;
    public int add(int x, int y) {
        // YOUR CODE HERE
        int i = 0;
        int remainder = 0;
        String equation = x + " + " + y;
        while (i < 32) {
            int bitx = getBit(x, i);
            int bity = getBit(y, i);
            if (bitx != bity && remainder == 0) {
                y = setBit1(y, i);
            } else if (bitx == 1 && bity ==1 && remainder == 0) {
                y = setBit0(y, i);
                remainder = 1;
            } else if (bitx == 0 && bity == 0 && remainder == 1) {
                y = setBit1(y, i);
                remainder = 0;
            } else if (bitx != bity && remainder == 1) {
                if (bity == 0) {
                    i += 1;
                    continue;
                } else {
                    y = setBit0(y, i);
                }
            } else if (bitx == bity && bity == 1 && remainder == 1) {
                remainder = 1;
            } 
            i += 1;
        }
        if (tacocat) {
            saveEquation(equation, y);
        }
        return y;
    }

    /** returns the ith bit from the right of x */
    private int getBit(int x, int i) {
        return (x >> i) & 1;
    }

    /** returns a new number equal to x but with bit i set to 1 */
    private int setBit1(int x, int i) {
        int noob = (1 << i);
        return x | noob;
    }

    /** returns a new number equal to x but with bit i set to 0 */
    private int setBit0(int x, int i) {
        int noob = (1 << i);
        return x ^ noob;
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
        tacocat = false;
        int neg = 0;
        String equation = x + " * " + y;
        if (x < 0) {
            x = add(~x, 1);
            neg = 1;
        }

        int product = y;
        while (x > 1) {
            product = add(y, product);
            x -= 1;
        }
        if (neg == 1 && y < 0 && product < 0) {
            return add(~product, 1);
        } else if (neg == 1 && product > 0) {
            return add(~product, 1);
        }
        saveEquation(equation, product);
        tacocat = true;
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
    EquationList history;
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
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
        EquationList mockHistory = history;
        while (mockHistory != null) {
            System.out.println(mockHistory.equation + " = " + mockHistory.result);
            mockHistory = mockHistory.next;
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
        EquationList mockHistory = history;
        while (mockHistory != null && n > 0) {
            System.out.println(mockHistory.equation + " = " + mockHistory.result);
            mockHistory = mockHistory.next;
            n -= 1;

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
        EquationList mockHistory = history;
        int totes = 0;
        while (mockHistory != null) {
            totes += mockHistory.result;
            mockHistory = mockHistory.next;
        }
        return totes;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList mockHistory = history;
        int totes = 1;
        while (mockHistory != null) {
            totes *= mockHistory.result;
            mockHistory = mockHistory.next;
        }
        return totes;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int common = x & y;
        int remaining = x ^ y;
        while (common != 0) {
            int commonCarried = common << 1;
            common = remaining & commonCarried;
            remaining = remaining ^ commonCarried;
        }
        return remaining;
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
        boolean negative = x > 0 && y < 0 || x < 0 && y > 0;
        if (x < 0) {
            x = add(~x, 1);
        }
        if (y < 0) {
            y = add(~y, 1);
        }
        int sum = 0;
        while (y > 0) {
            if ((y & 1) != 0) {
                sum = add(x, sum);
            }
            x = x << 1;
            y = y >> 1;
        }
        if (negative) {
            return add(~sum, 1);
        }
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
        // YOUR CODE HERE -- DONE
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
        // YOUR CODE HERE -- DONE
        EquationList historyMarker = history;
        while (historyMarker != null) {
            System.out.println(historyMarker.equation + " = " + historyMarker.result);
            historyMarker = historyMarker.next;
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
        // YOUR CODE HERE -- DONE
        EquationList historyMarker = history;
        for (int i = 0; i < n; i++) {
            if (historyMarker == null) {
                return;
            }
            System.out.println(historyMarker.equation + " = " + historyMarker.result);
            historyMarker = historyMarker.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE  -- DONE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE -- DONE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE -- DONE
        if (history == null) {
            return 0;
        }
        int sum = 0;
        EquationList historyMarker = history;
        while (historyMarker != null) {
            sum = add(sum, historyMarker.result);
            historyMarker = historyMarker.next;
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
        // YOUR CODE HERE -- DONE
        if (history == null) {
            return 1;
        }
        int product = 1;
        EquationList historyMarker = history;
        while (historyMarker != null) {
            product = multiply(product, historyMarker.result);
            historyMarker = historyMarker.next;
        }
        return product;
    }
}
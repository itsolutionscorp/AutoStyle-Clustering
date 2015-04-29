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
        if ((x & y) == 0) {
            return x | y;
        } else {
            int xAndYShiftedOne = (x & y) << 1;
            int aXorY = x ^ y;
            return add(xAndYShiftedOne, aXorY);
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
        if ((x == 0) || (y == 0)) {
            return 0;
        }
        if (y == 1) {
            return x;
        }
        int xReturn = x;
        while (y != 1) {
            xReturn = add(xReturn, x);
            y = add(y, -1);
        }
        return xReturn;
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
    private EquationList history;
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
        EquationList historyTemp = history;
        while (history != null) {
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
        }
        history = historyTemp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList historyTemp = history;
        while ((n != 0) && (history != null)) {
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
            n -= 1;
        }
        history = historyTemp;
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
        int resultHist = 0;
        EquationList historyTemp = history;
        while (history != null) {
            resultHist = add(resultHist, history.result);
            history = history.next;
        }
        history = historyTemp;
        return resultHist;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int resultHist = 1;
        EquationList historyTemp = history;
        while (history != null) {
            resultHist = multiply(resultHist, history.result);
            history = history.next;
        }
        history = historyTemp;
        return resultHist;
    }
}

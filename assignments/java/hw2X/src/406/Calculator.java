import list.EquationList;

public class Calculator {
    EquationList history = null;
    int numEquations = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS add() is a method which computes the
     * sum of two integers x and y using only bitwise operators.
     * 
     * @param x
     *            is an integer which is one of two addends
     * @param y
     *            is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry_over = (x & y) << 1;
        int res = x ^ y;
        if (carry_over == 0) {
            return res;
        }
        return this.add(res, carry_over);

    }

    public int subtract(int x, int y) {
        int res;
        y = this.add(~y, 1);
        res = this.add(x, y);

        return res;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS multiply() is a method which
     * computes the product of two integers x and y using only bitwise
     * operators.
     * 
     * @param x
     *            is an integer which is one of the two numbers to multiply
     * @param y
     *            is an integer which is the other of the two numbers to
     *            multiply
     * @return the product of x and y
     **/

    public int multiply(int x, int y) {
        int res = 0;
        while (y != 0) {
            if ((y & 0x01) != 0) {
                res = this.add(res, x);
            }
            x = x << 1;
            y = y >>> 1;
        }

        return res;
    }

    public int getFirstBinary1(int x) {
        int res = 0;

        return res;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and the
     * corresponding result. Note: You only need to save equations, not other
     * commands. See spec for details.
     * 
     * @param equation
     *            is a String representation of the equation, ex. "1 + 2"
     * @param result
     *            is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        EquationList cur = history;
        history = new EquationList(equation, result, cur);
        numEquations++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line. Please print in
     * the following format: Ex "1 + 2 = 3"
     **/
    public void printAllHistory() {
        this.printHistory(numEquations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS printHistory()
     * prints each equation (and its corresponding result), most recent equation
     * first with one equation per line. A maximum of n equations should be
     * printed out. Please print in the following format: Ex "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList cur = history;
        int count = 0;
        while (count < n && cur != null) {
            System.out.print(cur.equation + " = ");
            System.out.println(cur.result);
            cur = cur.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO undoEquation() removes the most recent equation we
     * saved to our history.
     **/
    public void undoEquation() {
        EquationList cur = history;
        history = cur.next;
        if (numEquations > 1) {
            numEquations--;
        } else {
            numEquations = 0;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        numEquations = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeSum() computes the
     * sum over the result of each equation in our history.
     * 
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList cur = history;
        int res = 0;
        int i = 0;
        while (i < numEquations) {
            res = this.add(res, cur.result);
            cur = cur.next;
            i++;
        }
        return res;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeProduct() computes
     * the product over the result of each equation in history.
     * 
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList cur = history;
        int res = 1;
        int i = 0;
        while (i < numEquations) {
            res = this.multiply(res, cur.result);
            cur = cur.next;
            i++;
        }
        return res;
    }
}
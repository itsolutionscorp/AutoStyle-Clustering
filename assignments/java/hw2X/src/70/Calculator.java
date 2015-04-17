import list.EquationList;

public class Calculator {
    EquationList eq = null;
    int eqcounter = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int res = x ^ y; 
        int carry = x & y;
        
        while (carry != 0) {
            int s = carry << 1;
            carry = res & s;
            res ^= s;
        }
        
        return res;
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
        int res = 0;
        if (x == 0 || y == 0) {
            res = 0;
        } else if ((x < 0) && (y < 0)) {
            x = add(~x, 1);
            y = add(~y, 1);
            for (int i = 0; i < y; ++i) {
                res = add(res, x);
            }
        } else {
            int inc;
            if (y < 0) {
                inc = -1;
                x = add(~x, 1);
            } else {
                inc = 1;
            }
            for (int i = 0; i != y; i = add(i, inc)) {
                res = add(res, x);
            }
        }
        return res;
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
        eq = new EquationList(equation, result, eq);
        ++eqcounter;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(eqcounter);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList eqp = eq;
        int counter = 0;
        while (eqp != null && counter++ < n) {
            System.out.println(eqp.equation + " = " + eqp.result);
            eqp = eqp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eq = eq.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eq = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int res = 0;
        EquationList eqp = eq;
        while (eqp != null) {
            res = add(res, eqp.result);
            eqp = eqp.next;
        }
        return res;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int res = 1;
        EquationList eqp = eq;
        while (eqp != null) {
            res = multiply(res, eqp.result);
            eqp = eqp.next;
        }
        return res;
    }
}

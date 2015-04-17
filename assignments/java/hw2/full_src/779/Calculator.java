import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList eqs;

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
        while (y != 0) {
            int c = x & y;
            x = x ^ y;
            y = c << 1;
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
        boolean isNegative = (x < 0) ^ (y < 0);
        if (x < 0) {x = add(~x, 1);}
        if (y < 0) {y = add(~y, 1);}
        int result = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                result = add(result, x);
            }
            x = x << 1;
            y = y >> 1;
        }
        if (isNegative) return add(~result, 1);
        return result;
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
        // YOUR CODE HERE
        EquationList eq = new EquationList(equation, result, null);
        if (eqs == null) {
            eqs = eq;
        } else {
            add(eq);
        }
    }

    private void add(EquationList eq) {
        eq.next = eqs;
        eqs = eq;
    }

    /**
     * Gets the number of equations currently stored
     */

    public int numEquations() {
        int i = 0;
        for (EquationList eq = eqs; eq != null; eq = eq.next) i++;
        return i;
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
        printHistory(numEquations());
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
        printEquations(eqs, n);
    }

    /**
     * Prints n equations after a given equation list
     **/ 
    private void printEquations(EquationList eq, int n) {
        if (eq == null) return;
        if (n != 0) {
            System.out.printf("%s = %d%n", eq.equation, eq.result);
            if (eq.next != null) {
                printEquations(eq.next, n-1);
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        eqs = eqs.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqs = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        for (EquationList eq = eqs; eq != null; eq = eq.next) {
            sum += eq.result;
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
        // YOUR CODE HERE
        int prod = 1;
        for (EquationList eq = eqs; eq != null; eq = eq.next) {
            prod *= eq.result;
        }
        return prod;
    }
}
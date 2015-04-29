import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eq;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        if (((x | y) << 1) == (x ^ y)) {
            return 0;
        }

        int hold = x & y;
        int ansr = x ^ y;

        while (hold != 0) {
            int mod = hold << 1;
            hold = ansr & mod;
            ansr = ansr ^ mod;
        }
        return ansr;

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
        } else if (x == 1) {
            return y;
        } else if (y == 1) {
            return x;
        }

        if (y > 0) {
            if ((y & 1) == 0) {
                return multiply((x << 1), (y >> 1));
            }
            return add(x, multiply(x, add(y, -1)));
        } else {
            if ((y & 1) == 0) {
                return multiply((x << 1), (y >> 1));
            }
            int z = add(x, multiply(x, add(y, 1)));
            return add(~z, 1);
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
        this.eq = new EquationList(equation, result, this.eq);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList list = eq;
        int n = 1;
        while (list != null) {
            printHistory(n);
            list = list.next;
            n = n + 1;
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
        EquationList list = eq;
        while (n > 1) {
            list = list.next;
            n = n - 1;
        }
        System.out.println(list.equation + " = " + list.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (eq != null) {
            eq = eq.next;
        } else {
            eq = null;
        }
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
        EquationList list = eq;
        int ans = 0;
        while (list != null) {
            ans = add(ans, list.result);
            list = list.next;
        }
        return ans;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList list = eq;
        int ans = 1;
        while (list != null) {
            ans = multiply(ans, list.result);
            list = list.next;
        }
        return ans;
    }
}
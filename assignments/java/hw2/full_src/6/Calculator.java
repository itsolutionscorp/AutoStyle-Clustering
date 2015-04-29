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
        // The approach is consider "y" to be the carry.
        //
        // Then, note that x^y is the sum of x and y whenever the digits are
        // different. For cases where both digits are set you also have some
        // extra work to do: the next digits will have some extra addition to
        // do. (x & y) captures the case of both digits set, the left shift
        // handles the fact that the "next" digits are affected.
        //
        // To see this, think of the case where the recursive call is a base
        // case: (x & y) << 1 is zero which, ignoring overflow, means (x & y) is
        // zero, meaning there are no cases of carry possible.
        if (y == 0) {
            return x;
        } else {
            return add(x ^ y, (x & y) << 1);
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
        if (y < 0) {
            return negate(multiply(x, negate(y)));
        }

        if (y == 0) {
            return 0;
        } else {
            return add(x, multiply(x, add(y, -1)));
        }
    }

    private int negate(int n) {
        return add(1, ~n);
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
        this.history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList list = history;
        while (list != null) {
            System.out.println(list.equation + " = " + list.result);
            list = list.next;
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
        EquationList list = history;
        int count = 0;
        while (list != null && count < n) {
            System.out.println(list.equation + " = " + list.result);
            list = list.next;
            count++;
        }
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
        EquationList list = history;
        int sum = 0;
        while (list != null) {
            sum += list.result;
            list = list.next;
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
        EquationList list = history;
        int product = 1;
        while (list != null) {
            product *= list.result;
            list = list.next;
        }

        return product;
    }
}

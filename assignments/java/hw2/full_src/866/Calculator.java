import list.EquationList;

public class Calculator {
    private EquationList equationList = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using
     * only bitwise operators.
     *
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     */
    public int add(int x, int y) {
        while (y != 0) {
            int t = x;
            x = x ^ y; // add bases
            y = (t & y) << 1; // set y to the carry
        }
        return x;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and
     * y using only bitwise operators.
     *
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     */
    public int multiply(int x, int y) {
        boolean negative = y < 0;
        if (negative) {
            y = add(-1, y);
            y = ~y;
        }

        int res = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                res = add(x, res);
            }
            x = x << 1;
            y = y >> 1;
        }

        if (negative) {
            res = add(~res, 1);
        }
        return res;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for
     * details.
     *
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result   is an integer corresponding to the result of the equation
     */
    public void saveEquation(String equation, int result) {
        equationList = new EquationList(equation, result, equationList);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     */
    public void printAllHistory() {
        EquationList cursor = equationList;
        while (cursor != null) {
            System.out.println(cursor);
            cursor = cursor.next;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     */
    public void printHistory(int n) {
        EquationList cursor = equationList;
        while (cursor != null && n > 0) {
            System.out.println(cursor);
            cursor = cursor.next;
            n -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
     */
    public void undoEquation() {
        equationList = equationList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     */
    public void clearHistory() {
        equationList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * `.
     *
     * @return the sum of all of the results in history
     */
    public int cumulativeSum() {
        int sum = 0;
        EquationList cursor = equationList;
        while (cursor != null) {
            sum = add(sum, cursor.result);
            cursor = cursor.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation
     * in history.
     *
     * @return the product of all of the results in history
     */
    public int cumulativeProduct() {
        int product = 1;
        EquationList cursor = equationList;
        while (cursor != null) {
            product = multiply(product, cursor.result);
            cursor = cursor.next;
        }
        return product;
    }
}
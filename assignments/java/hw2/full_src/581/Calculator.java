import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList equations;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = x;
        int carry = y;

        while (carry != 0) {
            int temp = sum ^ carry;
            carry = (sum & carry) << 1;
            sum = temp;
        }

        return sum;
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
        int product = 0;

        while (y != 0) {
            int digit = y & 1;

            if (digit != 0) {
                product = add(product, x);
            }

            x <<= 1;
            y >>>= 1;
        }

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
    public void saveEquation(String equation, int result) {
        equations = new EquationList(equation, result, equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(-1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList ptr = equations;

        // Any initial n < 0 will print all items in the history
        while (ptr != null && n != 0) {
            System.out.println(ptr.equation + " = " + ptr.result);

            ptr = ptr.next;
            --n;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (equations != null) {
            equations = equations.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList ptr = equations;
        int sum = 0;

        while (ptr != null) {
            sum += ptr.result;
            ptr = ptr.next;
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
        EquationList ptr = equations;
        int product = 1;

        while (ptr != null) {
            product *= ptr.result;
            ptr = ptr.next;
        }

        return product;
    }
}

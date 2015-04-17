import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList tracker;

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
        if (x == 0) {
            return y;
        }
        else {
            int a,b;
            while (y != 0) {
                b = (x & y) << 1;
                a = x ^ y;
                y = b;
                x = a;
            }
            return x;
        }
    }

    public boolean getbit(int num, int n) {
        return ((num >>> n) & 1) == 1;
    }

    public int setHigh(int x, int n) {
        return x | 1 << n;
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
        int product = 0;
        for (int index = 0; index < 32; index++) {

            if ((y >>> index & 1) == 1) {
                product = add(product, x << index);
            }
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
        EquationList newtracker = new EquationList(equation, result, null);
        newtracker.next = tracker;
        tracker = newtracker;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     */
    public void printAllHistory() {
        printHistory(-1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     */
    public void printHistory(int n) {
        EquationList current = tracker;

        while (n != 0 & current != null) {
            System.out.println(current.equation + " = " + current.result);
            current = current.next;
            n -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
     */
    public void undoEquation() {
        tracker = tracker.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     */
    public void clearHistory() {
        tracker = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     *
     * @return the sum of all of the results in history
     */
    public int cumulativeSum() {
        int sum = 0;
        EquationList leader = tracker;

        while (leader != null) {
            sum += leader.result;
            leader = leader.next;
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
        EquationList leader = tracker;

        while (leader != null) {
            product = product * leader.result;
            leader = leader.next;
        }
        return product;
    }
}
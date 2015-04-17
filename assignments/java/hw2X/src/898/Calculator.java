
import list.EquationList;

public class Calculator {

    EquationList list;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS add() is a method which computes the
     * sum of two integers x and y using only bitwise operators.
     *
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     *
     */
    public int add(int x, int y) {
        int temp;
        while (y != 0) {
            temp = x ^ y;
            y = (x & y) << 1;
            x = temp;
        }
        return x;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS multiply() is a method which
     * computes the product of two integers x and y using only bitwise
     * operators.
     *
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     *
     */
    public int multiply(int x, int y) {
        int sum = 0, tempX, tempY, shift, sign = 0;
        if (x << 1 >>> 1 != x) {
            x = add(~x, 1);
            sign = add(sign, 1);
        }
        if (y << 1 >>> 1 != y) {
            y = add(~y, 1);
            sign = add(sign, 1);
        }
        while (y != 0) {
            tempX = x;
            tempY = y;
            shift = 0;
            while (tempY != 1) {
                tempY = tempY >> 1;
                tempX = tempX << 1;
                shift = add(shift, 1);
            }
            sum = add(sum, tempX);
            y = add(y, add(~(1 << shift), 1));
        }
        return sign == 1 ? add(~sum, 1) : sum;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and the
     * corresponding result. Note: You only need to save equations, not other
     * commands. See spec for details.
     *
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     *
     */
    public void saveEquation(String equation, int result) {
        this.list = new EquationList(equation, result, this.list);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line. Please print in
     * the following format: Ex "1 + 2 = 3"
     *
     */
    public void printAllHistory() {
        EquationList lst = this.list;
        int n = 0;
        while (lst != null) {
            lst = lst.next;
            n = add(n, 1);
        }
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS printHistory()
     * prints each equation (and its corresponding result), most recent equation
     * first with one equation per line. A maximum of n equations should be
     * printed out. Please print in the following format: Ex "1 + 2 = 3"
     *
     */
    public void printHistory(int n) {
        EquationList lst = this.list;
        for (int i = 0; i < n; i = add(i, 1)) {
            System.out.println(lst.equation + " = " + lst.result);
            lst = lst.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO undoEquation() removes the most recent equation we
     * saved to our history.
     *
     */
    public void undoEquation() {
        this.list = this.list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO clearHistory() removes all entries in our history.
     *
     */
    public void clearHistory() {
        this.list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeSum() computes the
     * sum over the result of each equation in our history.
     *
     * @return the sum of all of the results in history
     *
     */
    public int cumulativeSum() {
        int sum = 0;
        EquationList lst = this.list;
        while (lst != null) {
            sum = add(sum, lst.result);
            lst = lst.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS cumulativeProduct() computes
     * the product over the result of each equation in history.
     *
     * @return the product of all of the results in history
     *
     */
    public int cumulativeProduct() {
        int product = 1;
        EquationList lst = this.list;
        while (lst != null) {
            product = multiply(product, lst.result);
            lst = lst.next;
        }
        return product;
    }
}

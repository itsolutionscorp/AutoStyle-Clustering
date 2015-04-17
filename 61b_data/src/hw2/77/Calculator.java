import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqHistory;
    int length = 0;

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
        int flip = x ^ y;
        int maskScoot = (x & y) << 1;
        if (maskScoot == 0) {
            return flip;
        } else {
            return addHelper(flip, maskScoot); 
        }
    }

    public int addHelper(int x, int y) {
        int mask = x & y;
        int set = x | y;
        return add(mask, set);
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
        if (y == 0 || x == 0) {
            return 0;
        } else if (y == 1) {
            return x;
        } else if (x == 1) {
            return y;
        } else if (y == add((~ 1), 1)) { //y == -1
            return add((~ x), 1);
        } else if (x == add((~ 1), 1)) {
            return add((~ y), 1);
        } else if ((y & 1) == 0) {
            return multiply(x << 1, y >> 1);
        } else {
            return add(x, multiply(x, add(y, add((~ 1), 1))));
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
        eqHistory = new EquationList(equation, result, eqHistory);
        length += 1;
        // YOUR CODE HERE
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
        printHistory(length);

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
        if (length == 0 && n != 0) {
        } else if (n > length) {
            printHistory(length);
        } else {
            EquationList temp = eqHistory;
            for (int i = 0; i < n; i++) {
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        eqHistory = eqHistory.next;
        length -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqHistory = null;
        length = 0;
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
        EquationList temp = eqHistory;
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                sum += temp.result;
                temp = temp.next;
            }
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
        int product = 1;
        EquationList temp = eqHistory;
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                product *= temp.result;
                temp = temp.next;
            }
        }
        return product;
    }
}
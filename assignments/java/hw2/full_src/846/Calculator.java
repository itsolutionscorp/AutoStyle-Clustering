import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equationHistory;
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
        int mask = x & y;
        int set = x | y;
        return helperAdd(mask, set);
    }

    public int helperAdd(int x, int y) {
        int flip = x ^ y;
        int left = (x & y) << 1;
        if (left == 0) {
            return flip;
        } else {
            return add(flip, left);
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
        if ((x == 0) || (y == 0)) {
            return 0;
        } else if (x == 1) {
            return y;
        } else if (y == 1) {
            return x;
        } else if (x == add(1, ~1)) {
            return add(~y, 1);
        } else if (y == add(1, ~1)) {
            return add(~x, 1);
        } else if ((y & 1) == 0) {
            return multiply(x << 1, y >> 1);
        } else {
            return add(x, multiply(x, add(y, add(1, ~1))));
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
        equationHistory = new EquationList(equation, result, equationHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int length = 0;
        EquationList copy = equationHistory;
        while (copy != null) {
            length = length + 1;
            copy = copy.next;
        }
        if (length == 0) {
            length = 1;
        }
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
        EquationList pseudoHistory = equationHistory;
        int i = 0;
        while ((pseudoHistory != null) && (i < n)) {
            System.out.println(pseudoHistory.equation + " = " + pseudoHistory.result);
            pseudoHistory = pseudoHistory.next;
            i = i + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList pointer = equationHistory;
        while (pointer != null) {
            sum = sum + pointer.result;
            pointer = pointer.next;
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
        int product = 1;
        EquationList pointer = equationHistory;
        while (pointer != null) {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    EquationList equationHistory = null;

    public int add(int x, int y) {
        if (y == 0) {
            return x;
        }
        else {
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
    private int actualMultiply(int x, int y) {
            x = Math.abs(x);
            y = Math.abs(y);
            int non_power = 0;
            final int original = x;

            int exponent = 0;
            while (Math.pow(2, exponent) <= y) {
                exponent++;
            }
            exponent = add(exponent, -1);
            int power = (int)(Math.pow(2, exponent));
            while (power != 1) {
                x = x << 1;
                power = power >> 1;
            }
            y = add(y, (int)(-Math.pow(2, exponent)));
            while (y != 0) {
                non_power = add(non_power, original);
                y = add(y, -1);
            }
            x = add(x, non_power);
            return x;

        }
    public int multiply(int x, int y) {
        if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
            return -actualMultiply(x, y);
        }
        return actualMultiply(x, y);   
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
        if (equationHistory == null) {
            equationHistory = new EquationList(equation, result, null);
        }
        else {
            equationHistory = new EquationList(equation, result, equationHistory);
        }
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
        EquationList tempHistory = equationHistory;
        while (tempHistory != null) {
            System.out.println(tempHistory.equation + " = " + tempHistory.result);
            tempHistory = tempHistory.next;
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
        // YOUR CODE HERE
        EquationList tempHistory = equationHistory;
        while (n != 0 && tempHistory != null) {
            System.out.println(tempHistory.equation + " = " + tempHistory.result);
            tempHistory = tempHistory.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (equationHistory != null) {
            equationHistory = equationHistory.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationHistory = null;
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
        EquationList tempHistory = equationHistory;
        while (tempHistory != null) {
            sum += tempHistory.result;
            tempHistory = tempHistory.next;
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
        EquationList tempHistory = equationHistory;
        while (tempHistory != null) {
            product *= tempHistory.result;
            tempHistory = tempHistory.next;
        }
        return product;
    }
}
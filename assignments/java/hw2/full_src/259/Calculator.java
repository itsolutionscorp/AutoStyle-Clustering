import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = new EquationList("0 + 0", 0, null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    /** return the ith bit from the right of x **/
    //Need to change back to private
    private int getBit(int x, int i) {
        x = x >> i;
        x = x << 31;
        x = x >>> 31;
        return x;
    }

    /** return a new number equal to x but with bit i set to 1 **/
    private int setBit(int x, int i) {
        int add1 = 1 << i;
        x = x | add1;
        return x;
    }

    public int add(int x, int y) {
        // YOUR CODE HERE
        int i = 0;
        int current = 0;
        int carried = 0;

        while (i < 32) {

            if (getBit(x, i) == 0) {
                if (getBit(y, i) == 1) {
                    if (carried == 0) {
                        current = setBit(current, i);
                    }
                }
                if (getBit(y, i) == 0) {
                    if (carried == 1) {
                        current = setBit(current, i);
                        carried = 0;
                    }
                }
            }
            
            if (getBit(x, i) == 1) {
                if (getBit(y, i) == 0) {
                    if (carried == 0) {
                        current = setBit(current, i);
                    }
                }
                if (getBit(y, i) == 1) {
                    if (carried == 0) {
                        carried = 1;
                    } else {
                        current = setBit(current, i);
                    }
                }
            }
            i = i + 1;
        }
        return current;
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
        int i = 0;
        int current = 0;
        int sign = 1;

        if (y < 0) {
            sign = -1;
            y = add(~y, 1);
        }

        while (i < y) {
            current = add(current, x);
            i = i + 1;
        }
        if (sign == 1) {
            return current;
        } else {
            return add(~current, 1);
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
        history = new EquationList(equation, result, history);
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
        EquationList currentHistory = history;
        while (currentHistory.next != null) {

            System.out.println(currentHistory.equation + " = " + currentHistory.result);
            currentHistory = currentHistory.next;
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
        int i = 1;
        EquationList currentHistory = history;
        while (i < n) {
            currentHistory = currentHistory.next;
            i = i + 1;
        }
        System.out.println(currentHistory.equation + " = " +currentHistory.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = new EquationList("0 + 0", 0, null);   
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
        EquationList currHistory = history;

        while (currHistory.next != null) {
            sum = add(sum, currHistory.result);
            currHistory = currHistory.next;
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
        EquationList currHistory = history;

        while (currHistory.next != null) {
            product = multiply(product, currHistory.result);
            currHistory = currHistory.next;
        }
        return product;
    }
}
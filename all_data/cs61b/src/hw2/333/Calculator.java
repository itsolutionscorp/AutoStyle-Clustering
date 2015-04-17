import list.EquationList;
 
public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList;
 
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
        int total = x ^ y;
        int carry = (x & y) << 1;
 
        if (y == 0) {
            return total;
        }
        return add(total, carry);
    }
 
    // ** returns the ith bit from the right of x */
    private int getBit(int x, int i) {
        // return x << 1 & i;
        return x & (1 << i);
    }
 
    // * returns a new number equal to x but with bit i set to 1 
    private int setBit(int x, int i) {
        return x | (1 << i);
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
        int num = 0;
        for (int i = 0; i < 32; i++) {
            if (getBit(y, 0) == 1) {
                num = add(x << i, num);
            }
            y = y >>> 1;
        }
        return num;
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
        eqList = new EquationList(equation, result, eqList);
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
        EquationList copyList = eqList;
        while (copyList != null) {
            System.out.println(copyList.equation + " = " + copyList.result);
            copyList = copyList.next;
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
        EquationList copyList = eqList;
        int i = 1;
        while (copyList != null) {
            if (i <= n) {
                System.out.println(copyList.equation + " = " + copyList.result);
            }
            i += 1;
            copyList = copyList.next;
        }
    }    
 
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        eqList = eqList.next;
    }
 
    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqList = null;
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
        EquationList copyList = eqList;
        while (copyList != null) {
            sum += add(copyList.result, 0);
            copyList = copyList.next;
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
        EquationList copyList = eqList;
        while (copyList != null) {
            product *= multiply(copyList.result, 1);
            copyList = copyList.next;
        }
        return product;
    }
}
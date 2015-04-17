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

    /* Finds and returns the ith bit from the right of x */

    public int getBit(int x, int i) {
        return ((x >>> i) & 1);
    }

    /* Returns a number equal to x but with the ith bit set to 1 */

    public int setBit(int x, int i) {
        return (x | (1 << i));
    }

    public int add(int x, int y) {
        // YOUR CODE HERE
        // int is 32 bit
        /* & (0&1 = 0, 0&0 = 0 1&1 =1)
         * | (1|0 = 1, 0|0 = 0, 1|1 = 1)
         * ^ (1^0 = 1, 0^0 = 0, 1^1 = 0)
         * ~ (~1 = 0, ~0 = 1)
         * >> (shift right, top bit copied)
         * >>> (shift right, top bit zero)
         * << (shift left, bottom bits zero)
         * x<<n = x*2 to the n
         * x>>n = x/2 to the n */
        int sum = 0;
        int carry = 0;
        for (int i = 0; i < 32; i++) {
            int ithBitX = getBit(x, i);
            int ithBitY = getBit(y, i);
            int ithBitCarry = getBit(carry, i);
            if ((((ithBitX == 1) && (ithBitY == 0)) || ((ithBitX == 0) && (ithBitY == 1))) && (ithBitCarry == 0)) {
                sum = setBit(sum, i);
            }
            else if ((((ithBitX == 1) && (ithBitY == 0)) || ((ithBitX == 0) && (ithBitY == 1))) && (ithBitCarry == 1)) {
                carry = setBit(carry, i+1);
            }
            else if ((ithBitX == 1) && (ithBitY == 1) && (ithBitCarry == 0)) {
                carry = setBit(carry, i+1);
            }
            else if (((ithBitX == 0) && (ithBitY == 0)) && (ithBitCarry == 1)) {
                sum = setBit(sum, i);
            }
            else if (((ithBitX == 1) && (ithBitY == 1)) && (ithBitCarry == 1)) {
                carry = setBit(carry, i+1);
                sum = setBit(sum, i);
            }
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
        // YOUR CODE HERE
        int product = 0;
        for (int i = 0; i < 32; i++) {
            int ithBitY = getBit(y, i);
            if (ithBitY == 1) {
                product = add(x << i, product);
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
    public int instantiate = 1;
    public EquationList called;
    public void saveEquation(String equation, int result) {
        if (instantiate == 1) {
            called = new EquationList(equation, result, null);
            instantiate = 0;
        }
        else {
            called = new EquationList(equation, result, called);
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
        EquationList copy = called;
        while (called != null) {
            System.out.println(called.equation + " = " + called.result);
            called = called.next;
        }
        called = copy;
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
        EquationList copy = called;
        for (int i = 0; i < n; i++) {
            System.out.println(called.equation + " = " + called.result);
            called = called.next;
        }
        called = copy;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        called = called.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        called = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int totSum = 0;
        EquationList copy = called;
        while (called != null){
            totSum = add(totSum, called.result);
            called = called.next;
        }
        called = copy;
        return totSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int totProd = 1;
        EquationList copy = called;
        while (called != null){
            totProd = multiply(totProd, called.result);
            called = called.next;
        }
        called = copy;
        return totProd;
    }
}
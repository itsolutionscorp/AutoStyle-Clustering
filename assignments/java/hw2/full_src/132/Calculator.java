import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

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
        /**
         * For this bitwise adder, we are flipping two numbers to get a basic
         * sum without any carry. To calculate the carry, we mask the two numbers
         * to find out which positions are adding 1 and 1, and then shift the carry
         * one position to the left. Then we flip the flipped with the carry,
         * to get another carry-less sum, calculate the new carry by masking the
         * previous sum with the carry, and then repeat until the carry is 0, which
         * indicates that there are no more carry operations left
         **/
        int flipped = x;
        int carry = y;
        int tempFlipped = flipped;
        while (carry != 0) {
            flipped = flipped ^ carry;
            carry = (carry & tempFlipped) << 1;
            tempFlipped = flipped;
        }
        return flipped;
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
        /**
         * This multiplier works by summing an x for every 1 in the binary
         * representation of y. The x's are shifted by the position of each one.
        **/

        // Base product is zero
        int product = 0;
        // keep adding x shifted appropriately for every y position with a 1 until
        // y is 0, in which the addition is finished
        while (y != 0) {
            // if there is a 1 in the first position, add x shifted by that position
            if ((y & 1) == 1) {
                product = add(product, x);
            }
            // shift x by 1 (multi by 2) for every position y goes to
            x = x << 1;
            y = y >>> 1;
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
        // YOUR CODE HERE
        EquationList p = history;
        while (n != 0 && p != null) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
            n -= 1;
        }
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
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList p = history;
        while (p != null) {
            sum = add(p.result, sum);
            p = p.next;
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
        EquationList p = history;
        while (p != null) {
            product = multiply(p.result, product);
            p = p.next;
        }
        return product;
    }
}
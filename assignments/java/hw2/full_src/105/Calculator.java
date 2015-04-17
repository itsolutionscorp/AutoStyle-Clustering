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
    public int add(int x, int y) {
        // YOUR CODE HERE
        /** 
         * if the last bit of x and y is 0, then result is 0, 0 + 0 = 0
         * if either is 1, then result is 1,  0 + 1 = 1
         * if both are 1, then adding x and y would increment the binary int to
         * a position on the left. On the last position, 1 + 1 = 0
         * we can implement ^ FLIP to represent the change made to the last bit 
         **/
        int result = x ^ y;
        /**
         * to implement the 1 + 1 operation, we would increment the position one step
         * to the left of current position. Thus to find cases of (1+1), we implement
         * AND, and shift the 1(if And returns True) one step to the left.
         */
        int increment = (x & y) << 1;
        /**
         * if there's no need to increment, meaning increment equals zero,
         * then we can simply return the result and be done with it. 
         * if increment isn't zero, then we'll recursively add the increment
         * to the result (or more accurately, the base of the ADD operation).
         */
        while (increment != 0) {
            return add(result, increment);
        }
        return result;
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
        /** Split y into addtions of various powers of 2, so x*y would be like
         * x * ( bit1(1 or 0)*2^0 + b2*2^1 + b3*2^2...).
         * for each seperate part, from the last position, if it's a 1, then
         * we know we need to multiply x by 2, meaning we add x to the current result.
         * however, we want the last position to be empty for next operation and 
         * to maintain the number, which means we need to shift x one step to the left.
         * one step to the left. And since we've processed this part of y,
         * we can trim y by shifting y one position to the right and truncate the last bit.
         */
        int result = 0;
        while (y!=0) {
            if((y & 1) != 0){
                result = add(result, x);
            }
            x <<= 1;
            y >>>= 1;
        }
        return result;
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
    EquationList dump = null;
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        dump = new EquationList(equation, result, dump);
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
        EquationList temp = dump;
        int count = 0;
        while (temp!= null){
            count += 1;
            temp = temp.next;
        }
        printHistory(count);
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
        EquationList temp = dump;
        while (n > 0){
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        dump = dump.next;
    
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        dump = null;
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
        EquationList temp = dump;
        while (temp != null){
            sum += temp.result;
            temp = temp.next;
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
        EquationList temp = dump;
        while (temp != null){
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}
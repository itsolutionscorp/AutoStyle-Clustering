import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // The running total sum
        int sum = 0;
        // Represents a carry from the last addition. Either 1 or 0
        int carry = 0;

        int val = 0;
        int xDig = 0;
        int yDig = 0;

        int runCount = 0;

        while ((x != 0) || (y != 0) || (carry != 0) && (runCount < 32)) {
            // Grab last digit of each number
            xDig = x & 1;
            yDig = y & 1;

            // Set the last-digit sum value
            val = (xDig ^ yDig ^ carry);

            // Set the carry value (for next computation)
            carry = (xDig & yDig) | (xDig & carry) | (yDig & carry);

            sum = (sum | (val << runCount));

            // Increase the significance to the next power of 2
            x = x >>> 1;
            y = y >>> 1;

            runCount += 1;
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

        // The running total product 
        int product = 0;
        // Tracks whether this digit needs to be calculated
        int multDig = 0;
        // Tracks how many times the loop has run
        int runCount = 0;
        // Represents x multiplied by some power of two
        int xPow2 = 0;
    
        while (y != 0) {
            multDig = y & 1;

            if (multDig == 1) {
            xPow2 = x << runCount;
                product = add(product, xPow2);
            }

            y = y >>> 1;
            runCount += 1;
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

        // if (history == null) {
        //     history = new EquationList(equation, result, null);
        // } else {
        //     EquationList ptr = history;
        //     
        //     // Get to the end of history (so deep)
        //     while (ptr.next != null) {
        //         ptr = ptr.next;
        //     }

        //     // Set the last entry
        //     ptr.next = new EquationList(equation, result, null);
        // }
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
        EquationList ptr = history;
        int count = 0;
        if (n == -1) {
            while (ptr != null) {
                System.out.println(ptr.equation + " = " + ptr.result);
                ptr = ptr.next;
            }
        } else {
            while ((ptr != null) && (count < n)) {
                System.out.println(ptr.equation + " = " + ptr.result);
                ptr = ptr.next;
                count += 1;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
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
        EquationList ptr = history;

        while (ptr != null) {
            sum = add(sum, ptr.result);
            ptr = ptr.next;
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
        EquationList ptr = history;

        while (ptr != null) {
            product = multiply(product, ptr.result);
            ptr = ptr.next;
        }

        return product;
    }
}

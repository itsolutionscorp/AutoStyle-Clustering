import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList equations;
    
    public Calculator() {
        equations = null;
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        /**
         * Iterative Method (Bitwise)
         */

        while (y != 0) {

            // find all remainders: (1) 101 & 001 -> 001 (2) 001 & 010 -> 000
            int remainders = x & y;

            // add numbers exclusively: (1) 101 ^ 001 -> 100 (2) RESULT: 100 ^ 010 -> 110
            x = x ^ y;

            // shift remainders left: (1) 001 -> 010 (2) 000 -> 000
            y = remainders << 1;

        }

        return x;
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

        /**
         * Iterative Method (Bitwise)
         */

        int result = 0;

        while (y != 0) {

            if ((y & 1) != 0) { // if is NOT a power of two
                result = add(result, x); // add the number being multiplied onto the result
            }

            x <<= 1; // "multiply" by two

            y >>>= 1; // "divide" by two

        }

        return result;

        /**
         * Recursive Method (Non-Bitwise)
         */

//        if (x == 0 | y == 0) {
//            return 0;
//        }
//
//        if (y < 0) {
//            return -multiply(x, -y);
//        }
//
//        if (y > 0) {
//            return add(x, multiply(x, add(y, -1)));
//        }
//
//        return x;
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
        equations = new EquationList(equation, result, equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {

        int i = 0;

        while (printHistory(i)) {
            i++;
        }

        if (i == 0) {
            System.out.println("History empty. Input an equation to get started.");
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public boolean printHistory(int n) {

        EquationList pointer = equations;
        int i = 0;

        while (i < n) {

            if (pointer == null) { // if index is beyond available history
                return false;
            }

            pointer = pointer.next;

            i++;
        }

        if (pointer != null) {
            System.out.println(pointer.equation + " = " + pointer.result);
        }

        return true;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        EquationList pointer = equations;
        int sum = 0;

        while (pointer != null) {
            sum += pointer.result;
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

        EquationList pointer = equations;
        int product = 1;

        while (pointer != null) {
            product *= pointer.result;
            pointer = pointer.next;
        }

        return product;
    }
}
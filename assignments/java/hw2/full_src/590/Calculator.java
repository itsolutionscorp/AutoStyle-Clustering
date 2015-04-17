import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList equationHist;
    private EquationList savePtr;
    private EquationList printPtr;
    private int equationCount = 0;

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
        int xor = x ^ y;
        int and = x & y;
        int carry = and << 1;
        int sum = xor | carry;
        and = xor & carry;
        while (and != 0){
            x = xor;
            y = carry;
            xor = x ^ y;
            and = x & y;
            carry = and << 1;
            sum = xor | carry;
            and = xor & carry;
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
        if (y == 2){
            return x << 1;
        }
        boolean invertX = false, invertY = false;
        if (x < 0){
            x = add(~x, 1);
            invertX = true;
        }
        if (y < 0){
            y = add(~y, 1);
            invertY = true;
        }
        /* This should work by adding together bit-shifted powers of two.
         * For example:
         *    0 0 0 1 1 0 (6)
         *  x 0 0 0 1 1 1 (7 = 2 + 4 + 1)
         *    ___________
         *    0 0 1 1 0 0  (the first 6 bit-shifted one place to multiply by 2)
         *  + 0 1 1 0 0 0  (the first 6 bit-shifted two places to multiply by 4)
         *  + 0 0 0 1 1 0  (the first 6 bit-shifted by 0 places to multiply by 1)
         *    ___________
         *    1 0 1 0 1 0 (42)
         *
         * So for every 1 in the multiplier I add a the multiplied number bit-shifted X
         * number of places where X is the index (counting from the right) of the 1 where 
         * the index count starts at 0 */
        int product = 0;
        int counter = 0;
        for (int i = 1; i < 32; i = multiply(i, 2)) {
            if ((y & i) == i){
                product = add(product, x << counter);
            }
            counter = add(counter, 1);
        }
        if (invertX){
            product = add(~product, 1);
        }
        if (invertY){
            product = add(~product, 1);
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
        // YOUR CODE HERE
        if (equationHist == null){
            equationHist = new EquationList(equation, result, null);
            savePtr = equationHist;
        } else {
            savePtr.next = new EquationList(equation, result, null);
            savePtr = savePtr.next;
        }
        equationCount = add(equationCount, 1);
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
        printHistory(equationCount);
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
        n = Math.min(n, equationCount);
        int index = equationCount;
        int stopCounter = add(equationCount, -n);
        while (index > stopCounter){
            printPtr = equationHist;
            for (int j = 1; j < index; j++){
                printPtr = printPtr.next;
            }
            System.out.println(printPtr.equation + " = " + printPtr.result);
            index = add(index, -1);
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        int i = 0;
        EquationList undoPtr = equationHist;
        int index = equationCount;
        if (equationCount != 0){
            for (int j = 1; j < index; j++){
                undoPtr = undoPtr.next;
            }
            undoPtr.next = null;
            savePtr = undoPtr;
            equationCount = add(equationCount, -1);
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationHist = null;
        savePtr = equationHist;
        printPtr = equationHist;
        equationCount = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList sumPtr = equationHist;
        int sum = 0;
        if (equationCount == 0){
            return 0;
        } else{
            while (sumPtr != null){
                sum = add(sum, sumPtr.result);
                sumPtr = sumPtr.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList prodPtr = equationHist;
        int prod = 1;
        if (equationCount == 0){
            return 1;
        }else{
            while (prodPtr != null){
                prod = multiply(prod, prodPtr.result);
                prodPtr = prodPtr.next;
            }
            return prod;
        }
    }
}
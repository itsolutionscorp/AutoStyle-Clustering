import list.EquationList;

public class Calculator {
    // Member Variables
    EquationList currentPointer = null;
    int sizeHistory = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // Works for single digits..
        // return ((x & y) << 1) | (y ^ x);

        int and;
        while (y != 0) {
            and = x & y;    // Carries the 01 + 01 = 10
            x = x ^ y;      // Adds the "one's" place. 00 + 01 = 01. 01 + 01 = 10
            y = and << 1;   // Assigns the carried 10 to y
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
        int product = 0;

        while (y != 0) {
            if ((y & 1) == 1)               // When the last term of y is 1
                product = add(product, x);  // Add x (l-shifted) to the product

            x = x << 1;                     // "Increment" x by 1
            y = y >>> 1;                    // "Decrement" y by 1
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
        // ! The History is LIFO !
        currentPointer = new EquationList(equation, result, currentPointer);
        sizeHistory += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     * ! LIFO !
     **/
    public void printAllHistory() {
        printHistory(sizeHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     * ! LIFO !
     **/
    public void printHistory(int n) {
        EquationList tempPointer = currentPointer;
        while (n != 0) {
            System.out.println(tempPointer.equation + " = " + tempPointer.result);
            tempPointer = tempPointer.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (currentPointer != null) {
            currentPointer = currentPointer.next;
            sizeHistory -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        currentPointer = null;
        sizeHistory = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList tempPointer = currentPointer;

        for (int i = 0; i < sizeHistory; i++) {
            sum += tempPointer.result;
            tempPointer = tempPointer.next;
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
        EquationList tempPointer = currentPointer;

        for (int i = 0; i < sizeHistory; i++) {
            product *= tempPointer.result;
            tempPointer = tempPointer.next;
        }
        
        return product;
    }
}
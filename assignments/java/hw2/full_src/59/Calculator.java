import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public int findBit(int a, int b) {
        return ((a << (31-b)) >>> 31);
        }

    public int putBit(int a, int b) {
        return (a | (1 << b));
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

        int carry = 0;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (findBit(x, i) == 0) {
                if (findBit(y, i) == 0 && (carry == 1)) {
                    carry = 0;
                    result = putBit(result, i);
                } else if (findBit(y, i) == 1 && (carry == 0)) {
                    result = putBit(result, i);
                }
            } else {
                if (findBit(y, i) == 0 && (carry == 0)) {
                    result = putBit(result, i);
                } else if (findBit(y, i) == 1) {
                    if (carry == 0) {
                        carry = 1;
                    } else {
                        result = putBit(result, i);
                    }
                }
            }
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
        int result = 0;
        if (y < 0) {
            return multiply(add(~x, 1), add(~y, 1));
        }
        for (int i = 0; i < y; i++) {
            result = add(result, x);
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

    EquationList history;
    EquationList copy;
    int numEntries = 0;

    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history);
        numEntries += 1;
    }
    
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        copy = history;
        for (int i = 0; i < numEntries; i++) {
            System.out.format(copy.equation + " = %d%n", copy.result);
            copy = copy.next;
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
        int temp = numEntries;
        numEntries = n;
        printAllHistory();
        numEntries = temp;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (numEntries > 0) {
            history = history.next;
            numEntries -= 1;            
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        numEntries = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        copy = history;
        for (int i = 0; i < numEntries; i++) {
            sum += copy.result;
            copy = copy.next;
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
        int prod = 1;
        copy = history;
        for (int i = 0; i < numEntries; i++) {
            prod *= copy.result;
            copy = copy.next;
        }
        return prod;
    }
}
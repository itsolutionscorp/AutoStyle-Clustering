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
        int currX = x;
        int oldX = x;
        int currY = y;
        int oldY = y;
        while (oldY != 0){
            currX = oldX ^ oldY;
            currY = (oldX & oldY) << 1;
            oldX = currX;
            oldY = currY;
        }
        return currX;
    }

    // Helper method that gets the bit at index of number. Bit 0 is on the very right. 
    private int getBit(int num, int index){
        int shifted = num >> index;
        return 1 & shifted;
    }

    // Helper method that negates an integer using bitwise operations.
    private int negate(int num){
        return add(~num, 1);
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
        if ((x == 0) || (y == 0)){
            return 0;
        }
        int bit;
        int sum = 0;
        int index = 0;
        while (y != 0){
            bit = getBit(y, index);
            if (bit == 1){
                sum = add(sum, x << index);
                y = add(y, negate(1 << index));
            }
            index = add(index, 1);
        }
        return sum;
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
    private EquationList history;

    public void saveEquation(String equation, int result) {
        if (history == null){
            history = new EquationList(equation, result, null);
        } else {
            history = new EquationList(equation, result, history);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    private EquationList hist;

    public void printAllHistory() {
        hist = history;
        if (hist == null){
            return;
        }
        while (hist != null){
            System.out.println(hist.equation + " = " + hist.result);
            hist = hist.next;
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
        hist = history;
        printHistoryHelper(n);
    }    

    private void printHistoryHelper(int n){
        if ((hist == null) || (n <= 0)){
            return;
        } else {
            System.out.println(hist.equation + " = " + hist.result);
            hist = hist.next;
            printHistoryHelper(n - 1);
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history == null){
            return;
        } else {
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
        hist = history;
        int sum = 0;
        while (hist != null){
            sum = add(sum, hist.result);
            hist = hist.next;
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
        hist = history;
        int prod = 1;
        while (hist != null){
            prod = multiply(prod, hist.result);
            hist = hist.next;
        }
        return prod;
    }
}
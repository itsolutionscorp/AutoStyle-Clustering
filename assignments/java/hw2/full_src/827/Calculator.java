import list.EquationList;

public class Calculator {
    public EquationList log = null; 
    

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
        while (y != 0) {
            int carry_over = x & y;
            x = x ^ y;
            y = carry_over << 1;
        }
        return x;
    }

    private int getBit(int x, int i) {
        //returns a 1 or 0
        return (x >>> i) & 1;
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
        int ans = 0;
        while (y != 0) {
            if (getBit(y, 0) == 1) { //checks if last bit 0 or 1
                ans = add(ans, x);
            }
            x = x << 1;
            y = y >> 1;
        }
        return ans;
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
        EquationList A = new EquationList(equation, result, log);
        log = A;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList log_copy = log;
        while (log != null) {
            System.out.println(log.equation + " = " + Integer.toString(log.result));
            log = log.next;
        }
        log = log_copy;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList log_copy = log;
        while (n > 0) {
            System.out.println(log.equation + " = " + Integer.toString(log.result));
            log = log.next;
            n = n - 1;
        }
        log = log_copy;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        log = log.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        log = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList log_copy = log;
        if (log == null) {
            return 0;
        }
        int tempSum = 0;
        while (log != null) {
            tempSum += log.result;
            log = log.next;
        }
        log = log_copy;
        return tempSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList log_copy = log;
        if (log == null) {
            return 1;
        }
        int tempProd = 1;
        while (log != null) {
            tempProd *= log.result;
            log = log.next;
        }
        log = log_copy;
        return tempProd;
    }
}
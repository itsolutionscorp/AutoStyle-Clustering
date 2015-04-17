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
        int n = 0;
        int s = 0;
        int b = 1;
        int c;
        for (int i = 0; i < 32; i ++) {
            c = b << i;
            if ((~(x | y) & c) == c) {//both digits are zero
                if (s == 1) {
                    n = n | c;
                    s = s ^ 1;
                }
            }
            else if (((x ^ y) & c) == c){//one of the digits is one
                if (s == 0) {
                    n = n | c;
                }
            }
            else if (((x & y) & c) == c){//both digits are one
                if (s == 1) {
                    n = n | c;
                    s = s ^ 1;
                }
                s = s ^ 1;
            }
        }
        return n;
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
        int n = 0;
        int z = ~ y;
        for (int i = 0; i < 32; i ++) {
            int c = 1 << i;
            if ((y & c) == c) {
                int a = x << i;
                n = add(n, a);
            }            
        }
        return n;
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
    EquationList nextEQ = null;

    public void saveEquation(String equation, int result) {
        nextEQ = new EquationList(equation, result, nextEQ);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (nextEQ != null) {
            System.out.println(nextEQ.equation + " = " + nextEQ.result);
            nextEQ = nextEQ.next;
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
        for (int i = 0; i < n; i ++) {
            System.out.println(nextEQ.equation + " = " + nextEQ.result);
            nextEQ = nextEQ.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        nextEQ = nextEQ.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        nextEQ = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int s = 0;
        while (nextEQ != null) {
            s += nextEQ.result;
            nextEQ = nextEQ.next;
        }
        return s;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int s = 1;
        while (nextEQ != null) {
            s *= nextEQ.result;
            nextEQ = nextEQ.next;
        }
        return s;
    }
}
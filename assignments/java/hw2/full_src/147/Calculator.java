import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList head = null;
    private int historySize = 0;

    /** 
     * Gets the kth bit of n. Right-most bit = 0. Left-most = 31 
     * @param n is the integer you want to retrieve the bit from
     * @param k is which bit to retrieve
     * @return the kth bit of n as an int (0 or 1)
     **/
    private int getBit(int n, int k) {
        return (n & (1 << k)) >>> k;
    }

    /**
     * Assigns a bit to be the value b (0 or 1).
     * @param n is the original int that will have its kth bit set
     * @param k is which bit to set
     * @param b is the value to set the bit to (0 or 1).
     * @return n with its kth bit set to b
     **/
    private int setBitTo(int n, int k, int b) {
        if (b == 0)
            return n & ~(1 << k);
        return n | (1 << k);
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
        int result = 0, bit1 = 0, bit2 = 0, carry = 0;

        /* Iterate through each bit */
        for (int i = 0; i < 32; i += 1) {
            bit1 = getBit(x, i); bit2 = getBit(y, i);

            if (((carry ^ bit1 ^ bit2) == 1) || ((carry & bit1 & bit2) == 1))
                result = setBitTo(result, i, 1);

            if (((carry & bit1) == 1) || ((carry & bit2) == 1) || ((bit1 & bit2) == 1)) {
                carry = 1;
            } else {
                carry = 0;
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

        /* Iterate through each bit */
        for (int i = 0; i < 32; i += 1) {
            if (getBit(y, i) == 1) {
                result = add(result, x << i);
            }
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
    public void saveEquation(String equation, int result) {
        if (head == null) {
            head = new EquationList(equation, result, null);
        } else {
            head = new EquationList(equation, result, head);
        }
        historySize += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historySize);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList curr = head;
        for (int i = 0; i < n; i += 1) {
            if (curr == null)
                return;

            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (head != null)
            head = head.next;
        historySize -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        head = null;
        historySize = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList curr = head;
        int accumulation = 0;
        while (curr != null) {
            accumulation += curr.result;
            curr = curr.next;
        }
        return accumulation;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList curr = head;
        int accumulation = 1;
        while (curr != null) {
            accumulation *= curr.result;
            curr = curr.next;
        }
        return accumulation;
    }
}
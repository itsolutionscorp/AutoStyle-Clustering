import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

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
        int carry = 0;
        int curBit = 0;
        int result = 0;
        int andBitCheck;
        int flipBitCheck;
        int orBitCheck;
        while (curBit < 32) {
            andBitCheck = getBit(x, curBit) & getBit(y, curBit);
            flipBitCheck = getBit(x, curBit) ^ getBit(y, curBit);
            orBitCheck = getBit(x, curBit) | getBit(y, curBit);
            if (andBitCheck == 1 && carry == 1) {
                result = setBit(result, curBit);
            } else if (andBitCheck == 1 && carry == 0) {
                carry = 1;
            } else if (flipBitCheck == 1 && carry == 0) {
                result = setBit(result, curBit);
            } else if (orBitCheck == 0 && carry == 1) {
                result = setBit(result, curBit);
                carry = 0;
            }
            curBit = curBit + 1;
        }
        return result;
    }

    private int getBit(int x, int pos) {
        x = x >> pos;
        x = x << 31;
        x = x >>> 31;
        return x;
    }

    private int setBit(int x, int pos) {
        return x | (1 << pos);
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
        int temp = x;
        int result = 0;
        int curBit = 0;
        int curYBit;
        int toAdd;
        while (curBit < 32) {
            curYBit = getBit(y, curBit);
            if (curYBit == 1) {
                toAdd = (x & 2147483647) << curBit;
                result = add(result, toAdd);
            }
            curBit = curBit + 1;
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
        // YOUR CODE HERE
        history = new EquationList(equation, result, history);    
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
        int n = 0;
        EquationList cur = history;
        while (cur != null) {
            n = n + 1;
            cur = cur.next;
        }
        printHistory(n);
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
        EquationList cur = history;
        while (n != 0 && cur != null) {
            System.out.println(cur.equation + " = " + cur.result);
            cur = cur.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (history != null) {
            history = history.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int result = 0;
        EquationList cur = history;
        while (cur != null) {
            result = result + cur.result;
            cur = cur.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int result = 1;
        EquationList cur = history;
        while (cur != null) {
            result = result * cur.result;
            cur = cur.next;
        }
        return result;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList save;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    // public int add(int x, int y) {
    //     while (y != 0) {
    //         int or = x ^ y;
    //         int carry = (y & x) << 1;
    //         x = or ^ carry;
    //         y = carry & or;
    //     }
    //     return x;
    //}
    public int add(int x, int y) {
        int xor = x ^ y;
        int carry = x & y;
        while (carry != 0) {
            int carryShift = carry << 1;
            carry = xor & carryShift;
            xor = xor ^ carryShift;
        }
        return xor;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    // public int multiply(int x, int y) {
    //     // YOUR CODE HERE
    //     int orig = x;
    //     String binaryString = Integer.toBinaryString(y)
    //     if (x == 0 || y == 0) {
    //         return 0;
    //     }
    //     else if (y == 1) {
    //         return x;
    //     }
    //     else if (getBit(y & 1) == 1) {
    //         return multiply(add(x, orig), y - 1);
    //     }
    //     else {
    //         return multiply(add(x, x), y >>> 1);
    //     }    
    // }
// WRITE GETFIRSTBIT PLS
    public int multiply(int x, int y) {
        int result = 0;
        int orig = x;
        if (x == 0 || y == 0) {
            return 0;
        }
        else {
            while (y != 1) {
                if (getFirstBit(y) == 1) {
                    result = add(x, result);
                } 
                x = add(x, x);
                y = y >>> 1;
            }
            return x + result;
        }
    }

    private int getFirstBit(int num) {
        String temp = Integer.toBinaryString(num);
        String firstBit = temp.substring(temp.length() - 1);
        int bit = Integer.parseInt(firstBit);
        return bit;
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
    // INSTANTIATE LIST IN METHOD
    // CREATE VARIABLE IN CLASS TO HOLD SAVEEQUATIOn
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        String eq = equation;
        int res = result;
        if (save == null) {
            save = new EquationList(equation, result, null);   
        }
        else {
            save = new EquationList(equation, result, save);
        }
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
        while (save != null) {
            System.out.println(save.equation + " = " + save.result);
            save = save.next;
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
        // YOUR CODE HERE
        while (n > 0) {
            System.out.println(save.equation + " = " + save.result);
            save = save.next;
            n = n - 1;            
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        save = new EquationList(save.next.equation, save.next.result, save.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        save = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int res = 0;
        if (save == null) {
            return res;
        }
        else {
            while (save != null) {
                res = res + save.result;
                save = save.next;
            }
            return res;
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
        int res = 1;
        if (save == null) {
            return res;
        }
        else {
            while (save != null) {
                res = res * save.result;
                save = save.next;
            }
            return res;
        }
    }
}
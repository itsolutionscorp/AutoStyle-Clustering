import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    private int getBit(int x, int i) {
        // returns ith bit from the right of x
        String xBin = Integer.toBinaryString(x);

        if (i > (xBin.length()-1)) {
            if (x >= 0) {
                return 0;
            } 
            else if (x < 0) {
                return 1;
            }
        }
        char bitString = xBin.charAt(xBin.length() - 1 - i);
        int bit = Character.getNumericValue(bitString);
        return bit;
    }

    public int add(int x, int y) {
        int xBit;
        int yBit;
        String zString = null;
        Long zLong;
        int z;
        int carry = 0;

        for (int i = 0; i < 32; i = i+1) {
            xBit = getBit(x, i);
            yBit = getBit(y, i);

            if (carry == 0) {
                if ((xBit ^ yBit) == 1) {
                    if (zString == null) {
                        zString = Character.toString('1');
                    }
                    else {
                    zString = '1' + zString;
                    }
                    carry = 0;
                }
                else if ((xBit & yBit) == 1) {
                    carry = 1;
                    if (zString == null) {
                        zString = Character.toString('0');
                    }
                    else {
                    zString = '0' + zString;
                    }
                }
                else if ((xBit & yBit) == 0) {
                    carry = 0;
                    if (zString == null) {
                        zString = Character.toString('0');
                    }
                    else {
                        zString = '0' + zString;
                    }
                }
            }

            else if (carry == 1) {
                if ((xBit ^ yBit) == 1) {
                    carry = 1;
                    zString = '0' + zString;
                }
                else if ((xBit & yBit) == 0) {
                    zString = '1' + zString;
                    carry = 0;
                }
                else if ((xBit & yBit) == 1) {
                    carry = 1;
                    zString = '1' + zString;
                }
            }
        } 

        zLong = Long.parseLong(zString,2);
        z = zLong.intValue();
        return z;
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
        int z = 0;
        int yAbs = Math.abs(y);

        if (y == 1) {
            z = x;
        }
        else if (y == 2) {
            z = x << 1;
        }
        else {
            for (int i = yAbs; i >= 1; i = i -1) {
                z = add (z, x);
            }
        }

        if (y < 0) {
            z = -z;
        }
        return z;
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else {
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
    public void printAllHistory() {
        EquationList ptr = history;

        while (ptr != null) {
            System.out.println(ptr.equation + " = " + ptr.result);
            ptr = ptr.next;
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
        EquationList ptr = history;

        for (int i = 1; i <= n; i = i + 1) {
            if (ptr == null) {
                break;
            }
            else {
                System.out.println(ptr.equation + " = " + ptr.result);
            }
            ptr = ptr.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
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
        int sum = 0;
        EquationList ptr = history;
        while (ptr != null) {
            sum = sum + ptr.result;
            ptr = ptr.next;
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
        EquationList ptr = history;
        while (ptr != null) {
            product = product * ptr.result;
            ptr = ptr.next;
        }
        return product;
    }
}
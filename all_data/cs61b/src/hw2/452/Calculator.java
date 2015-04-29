import list.EquationList;

public class Calculator {
    private EquationList history;
    private int historySize, cumSum;
    private int cumProduct = 1;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int testBit = 0b1;
        boolean carry = false;
        
        while (y != 0 || carry) {
            if ((y & testBit) != 0) {
                if ((x & testBit) != 0) {
                    if (!carry) {
                        carry = true;
                        x &= ~testBit;
                    } 
                } else { 
                    if (!carry) {
                        x |= testBit; 
                    }
                }
            } else {
                if (carry) {
                    if ((x & testBit) == 0) {
                        carry = false;
                        x |= testBit;
                    } else {
                        x &= ~testBit;
                    }
                }
            }
            
            y &= ~testBit;
            testBit <<= 1;
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
        int bitNum = 0;
        
        while (y != 0) {
            if ((y & 1) != 0) { product = add(product, x << bitNum); }
            y >>>= 1;
            bitNum += 1;
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
        history = new EquationList(equation, result, history);
        historySize += 1;
        cumSum += result;
        cumProduct *= result;    
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
        EquationList runner = history;
        while (n > 0 && runner != null) {
            System.out.println(runner.equation + " = " + runner.result);
            runner = runner.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            cumSum -= history.result;
            cumProduct /= history.result;
            history = history.next;
            historySize -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        historySize = 0;
        cumSum = 0;
        cumProduct = 1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        return cumSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        return cumProduct;
    }
}
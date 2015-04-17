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
    public int add(int x, int y) {
        int result = 0;
        int rollover = 0;
        int whichBit = 1;
        for(int i = 0; i < 32; i++) {
            if((x & whichBit) != 0 && (y & whichBit) != 0) {
                if(rollover == 1) {
                    result = result ^ whichBit;
                    } else {
                        rollover = 1;
                    }
            } else if(((x & whichBit) != 0) || ((y & whichBit) != 0)) {
                    if(rollover != 1) {
                        result = result ^ whichBit;
                }
            } else {
                if(rollover == 1) {
                    result = result ^ whichBit;
                    rollover = 0;
                }
            }
                whichBit = whichBit << 1;
            }
        return  result;
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
        int twofactor = 1;
        int result;
        if(x == 0 || y == 0) {
            return 0;
        }
        if(x == 1) {
            return y;
        }
        if(y == 1) {
            return x;
        }
        if(y < 0) {
            y = add(0, -y);
            x = add(0, -x);
        }
        result = x;
        while((twofactor << 1) < y) {
            result = result << 1;
            twofactor = twofactor << 1;
        }
        y = add(y, -twofactor);
        while(y > 0) {
            result = add(result, x);
            y = add(y, -1);
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
        EquationList historyIterator = history;
        while(historyIterator != null) {
            System.out.println(historyIterator.equation + " = " + historyIterator.result);
            historyIterator = historyIterator.next;
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
        EquationList historyIterator = history;
        for(int i = 0; i < n; i++) {
        if(historyIterator == null) {
            break; 
        }
            System.out.println(historyIterator.equation + " = " + historyIterator.result);
            historyIterator = historyIterator.next;
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
        EquationList historyIterator = history;
        int sum = 0;
        while(historyIterator != null) {
            sum = add(sum, historyIterator.result);
            historyIterator = historyIterator.next;
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
        EquationList historyIterator = history;
        int product = 1;
        while(historyIterator != null) {
            product = multiply(product, historyIterator.result);
            historyIterator = historyIterator.next;
        }
        return product;
    }
}

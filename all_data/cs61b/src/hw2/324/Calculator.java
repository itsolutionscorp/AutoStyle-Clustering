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
    public EquationList history;
    public int historyCnt = 0;

    public int add(int x, int y) {
        // YOUR CODE HERE
        //have a hold with zero in it
        //push things so we get the current power in the very front followed by zeros
        //flip imprint onto zero
        //move zero over
        //mask the addens and imprint on to zero
        //iterate
        int flip = x;
        int mask = y;
        int holdFlip = x;
        int holdMask;
        for(int i = 0; i < 32; i++) {
            holdFlip = flip;
            holdMask = mask;
            flip = holdFlip ^ holdMask;
            mask = holdFlip & holdMask;
            mask = mask << 1;     

        }
        return flip;
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
        // have moving pointer bit
        // mask it to current power of 2
        // push it to cover all 32 bits
        //mask that with other number
        //push the masked value over to the needed power of two
        //add to holder whisch starts at zero
        int accumulate = 0;
        int adder = 0;
        int mulFactor = 0;
        for(int i = 31; i >= 0; i--) {
            mulFactor = y >> 31;
            adder = x & mulFactor;
            adder = adder << i;
            accumulate = add(accumulate, adder);
            y = y << 1;
        }
        return accumulate;
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
        historyCnt += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historyCnt);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList historyPointer = history;
        while ((n > 0) && historyPointer != null) {
            System.out.println(historyPointer.equation + " = " + historyPointer.result);
            historyPointer = historyPointer.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
            historyCnt -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        historyCnt = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList historyCol = history;
        int sum = 0;
        while (historyCol != null) {
            sum += historyCol.result;
            historyCol = historyCol.next;
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
        EquationList historyCol = history;
        int prod = 1;
        while (historyCol != null) {
            prod *= historyCol.result;
            historyCol = historyCol.next;
        }
        return prod;
    }
}
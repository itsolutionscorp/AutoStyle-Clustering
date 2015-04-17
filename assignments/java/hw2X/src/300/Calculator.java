import list.EquationList;

public class Calculator {
    public EquationList myHistory = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int i = 0;
        int carryover = 0;
        int answer = 0;
        while (i < 32) {
            int xbit = getBit(x, i);
            int ybit = getBit(y, i);
            if (((xbit == 1) && (ybit == 0)) | ((xbit == 0) && (ybit == 1))) {
                if (carryover == 0) {
                answer = setBit(answer, i);
                carryover = 0;
            } else if (carryover == 1) {
                carryover = 1;}
            } else 
            if ((xbit == 0) && (ybit == 0)) {
                if (carryover == 1) {
                    answer = setBit(answer, i);
                    carryover = 0;
                }
            } else
            if (carryover == 0) {
                carryover = 1;
            } else {
                answer = setBit(answer, i);
                carryover = 1;
            }
            i += 1;
        }
        return answer;
    }

// getBit takes in an integer x and an index i and returns the i'th bit of the binary
    // form of x (either 0 or 1).
    public int getBit(int x, int i) {
        int a = x >>> i;
        int b = a & 1;
        if (b == 1) {return 1;}
        return 0;
    }

// setBit takes in an integer x and an index i and returns an integer whose binary string is 
    // identical to x's binary string except with the i'th bit changed to 1.
    public int setBit(int x, int i) {
        int a = x >>> i;
        int b = a ^ 1;
        int c = b << i;
        int d = x | c;
        return d;
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
        if ((x == 0) | (y == 0)) {
            return 0;
        }
        int answer = 0;
        int i = 0;
        while (i < 32) {
            int ybit = getBit(y, 0);
            if (ybit == 1) {
                answer = add(answer, x);
            }
            x = x << 1;
            y = y >>> 1;
            i += 1;
        }
        return answer;
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
        EquationList newentry = new EquationList(equation, result, myHistory);
        myHistory = newentry;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int histlen = historyLength();
        printHistory(histlen);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (n == 0) {
            return;
        }
        EquationList pointer = myHistory;
        while ((n > 0) && (pointer != null)) {
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
            n -= 1;
            pointer = pointer.next;
        }
    }    

    public int historyLength() {
        int len = 0;
        EquationList pointer = myHistory;
        while (pointer != null) {
            len += 1;
            pointer = pointer.next;
        }
        return len;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        myHistory = myHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        myHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = myHistory;
        int total = 0;
        while (pointer != null) {
            total += pointer.result;
            pointer = pointer.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList pointer = myHistory;
        int total = 1;
        while (pointer != null) {
            total = total * pointer.result;
            pointer = pointer.next;
        }
        return total;
    }
}

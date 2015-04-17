import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equationHistory;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    /**
     * Massive shout-out and thanks to JHug's video explanation for the concept of the
     * CARRY variable. */
    public int add(int x, int y) {
        int i = 0;
        while (y != 0 || i < 32) {
            int carry = (x & y);
            x =  x ^ y;
            y = carry << 1;
            i += 1;
        }
        return x;
    }

    /**
     * The getBit and setBit methods are unused by the add method, but were painstakingly
     * crafted, and so left in. */
    public int getBit(int num, int pos) {
        int shifted = num >> pos;
        return shifted & 1;
    }
    public int setBit(int num, int pos) {
        int shifted = num >> pos;
        shifted = shifted | 1;
        shifted = shifted << pos;
        return shifted | num;
    }


    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    /**
     * Algorithm of breaking Y into powers of 2 thanks to
     * http://stackoverflow.com/questions/12633240/bit-shift-multiplication-loop
     */
    public int multiply(int x, int y) {
        int i = 0;
        int running = 0;
        while (i < 32) {
            if (getBit(y, i) == 1) {
                running = add(running, x << i);
            }
            i += 1;
        }
        return running;
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
        equationHistory = new EquationList(equation, result, equationHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int histLength = 1;
        if (equationHistory == null) {
            return;
        }
        EquationList tracer = equationHistory;
        while (tracer.next != null) {
            histLength += 1;
            tracer = tracer.next;
        }
        printHistory(histLength);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointer = equationHistory;
        if (equationHistory == null) {
            return;
        }
        while (n > 0) {
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
            pointer = pointer.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (equationHistory != null) {
            equationHistory = equationHistory.next;
        }

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (equationHistory == null) {
            return 0;
        }
        int sum = 0;
        EquationList p = equationHistory;
        while (p != null) {
            sum += p.result;
            p = p.next;
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
        if (equationHistory == null) {
            return 1;
        }
        int sum = 1;
        EquationList p = equationHistory;
        while (p != null) {
            sum *= p.result;
            p = p.next;
        }
        return sum;
    }
}
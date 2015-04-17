import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private boolean debug = true;
    private EquationList history = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int level = 0;
        int result = 0;
        int a, b, microresult;
        boolean flagCarry = false;
        while ((x | y) != 0 || flagCarry) {
            //truncate
            if (level >= 32)
                break;

            a = x & 1;
            b = y & 1;

            if ((a ^ b) == 1) {
                if (flagCarry) {
                    // 1 + (1) = 10 so continue carrying a 1
                    microresult = 0;
                } else {
                    microresult = 1;
                }
            } else if ((a & b) == 1) {
                microresult = flagCarry ? 1 : 0;
                flagCarry = true;
            } else {
                if (flagCarry) {
                    //0 + (carried 1) = 1 so cease carrying a 1
                    flagCarry = false;
                    microresult = 1;
                } else {
                    microresult = 0;
                }
            }

            result |= microresult << level;
            x >>>= 1;
            y >>>= 1;
            level++;

        }
        return result;
    }

    private void trace(String msg) {
        if (!debug)
            return;
        System.out.println(msg);
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
        int bits = 32;
        int bit = 0;
        int result = 0;
        int yMask;

        while (bit < bits) {
            yMask = y & 1;
            if (yMask == 1) {
                result = add(result, x);
            }
            x <<= 1;
            y >>>= 1;
            bit++;
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
        EquationList isadora = history;
        while (isadora != null) {
            System.out.printf("%s = %d\n", isadora.equation, isadora.result);
            isadora = isadora.next;
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
        //bifurcate(U1, U2) <<< mobius double-reacharound
        EquationList sconce = history;
        while (sconce != null && n-- > 0) {
            System.out.printf("%s = %d\n", sconce.equation, sconce.result);
            sconce = sconce.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null)
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
        int result = 0;
        for(int num : new TemporalIterator(history)) {
            result += num;
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
        int result = 1;
        for(int num : new TemporalIterator(history)) {
            result *= num;
        }
        return result;
    }

    private class TemporalIterator 
            implements java.util.Iterator<Integer>, Iterable<Integer>
    {
        EquationList history;
        public TemporalIterator(EquationList history) {
            this.history = history;
        }

        public boolean hasNext() {
            return history != null;
        }
        public Integer next() {
            int denizen = history.result;
            history = history.next;
            return denizen;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public java.util.Iterator<Integer> iterator() {
            return this;
        }
    }
}

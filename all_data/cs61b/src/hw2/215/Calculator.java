import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
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
        if (y == 0) {
            return x;
        }
        return add(x ^ y, (x & y) << 1);
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
        int res = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                res = add(res, x);
            }
            x <<= 1;
            y >>>= 1;
        }
        return res;
    }


    /**
     * Returns last element of an IntList.
     * @param L The IntList.
     */
    private EquationList last(EquationList L) {
        while (L.next != null) {
            L = L.next;
        }
        return L;
    }

    private EquationList butLast(EquationList L) {
       while (L.next.next != null) {
           L = L.next;
       }
       return L;
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else {
            EquationList lastNode = last(history);
            lastNode.next = new EquationList(equation, result, null);
        }
    }

    private EquationList reverse(EquationList L) {
        EquationList rev = null;
        while (L != null) {
            rev = new EquationList(L.equation, L.result, rev);
            L = L.next;
        }
        return rev;
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
        EquationList L = reverse(history);
        while (L != null) {
            StdOut.print(L.equation);
            StdOut.print(" = ");
            StdOut.println(L.result);
            L = L.next;
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
        EquationList L = reverse(history);
        for (int i = 0; i < n; i++) {
            if (L == null) {
                break;
            }
            StdOut.print(L.equation);
            StdOut.print(" = ");
            StdOut.println(L.result);
            L = L.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList L = butLast(history);
        L.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList L = history;
        int res = 0;
        while (L != null) {
            res = add(res, L.result);
            L = L.next;
        }
        return res;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList L = history;
        int res = 1;
        while (L != null) {
            res = multiply(res, L.result);
            L = L.next;
        }
        return res;
    }
}

import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = new EquationList(null, null, 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0) {
            int combo = x & y;
            x = x ^ y;
            combo = combo << 1;
            y = combo;
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
        int total = 0;
        if (y < 0) {
            y = add(~y, 1);
            if (x < 0) {
                x = add(~x, 1);
            }
        }
        for (int i = 0; i < y; i++) {
            total = add(total, x);
        }

        return total;
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
        if (history.equation == null) {
            history.equation = equation;
            history.result = result;
        } else {
            EquationList tracker = history;
            while(tracker.next != null) {
                tracker = tracker.next;
            }
            tracker.next = new EquationList(tracker, equation, result, null);
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
        int length = 1;
        EquationList tracker = history;
        while(tracker.next != null) {
            tracker = tracker.next;
            length++;
        }
        printHistory(length);

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList tracker = history;
        while(tracker.next != null) {
            tracker = tracker.next;
        }

        for (int i = 0; i < n; i++) {
            if (tracker.equation != null) {              
                System.out.println(tracker.equation + " = " + tracker.result);
            }
            tracker = tracker.front;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList tracker = history;
        while(tracker.next.next != null) {
            tracker = tracker.next;
        }
        tracker.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList(null, null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        EquationList tracker = history;
        while(tracker != null) {
            total += tracker.result;
            tracker = tracker.next;
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
        int total = 1;
        EquationList tracker = history;
        while(tracker != null) {
            total *= tracker.result;
            tracker = tracker.next;
        }
        return total;
    }
}
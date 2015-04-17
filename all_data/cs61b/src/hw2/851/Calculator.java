import list.EquationList;

public class Calculator {
    public EquationList eqHistory = null;

    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) {
        int track = x & y;
        int flip = x ^ y;
        if (x == y) {
            return (x << 1);
        }
        else if ((x & y) == 0) {
            return (x | y);
        }
        else {
            return add(flip, (track << 1));
        }
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
        int result = 0;
        if (x == 0 || y == 0) {
            return 0;
        }
        else if (x == 1) {
            return y;
        }
        else if (y == 1) {
            return x;
        }
        else if (x < 0 && y < 0) {
            return multiply(add((~x), 1), add((~y), 1));
        }
        else if (x < 0 && y > 0) {
            return add(~(multiply(add((~x), 1), y)), 1);
        }
        else if (x > 0 && y < 0) {
            return add(~(multiply(x, add((~y), 1))), 1);
        }
        else {
            while (y > 0) {
                result = add(result, x);
                y -= 1;
            }
            return result;
        }
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
        eqHistory = new EquationList(equation, result, eqHistory);
    } 

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList track = eqHistory;
        while (track != null) {
            System.out.println(track.equation + " = " + track.result);
            track = track.next;
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
        EquationList track = eqHistory;
        while (n > 0 && track != null) {
            System.out.println(track.equation + " = " + track.result);
            track = track.next;
            n -= 1;
        }
    }  

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqHistory = eqHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList track = eqHistory;
        int result = 0;
        while (track != null) {
            result = add(result, track.result);
            track = track.next;
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
        EquationList track = eqHistory;
        int result = 1;
        while (track != null) {
            result = multiply(result, track.result);
            track = track.next;
        }
        return result;
    }
}
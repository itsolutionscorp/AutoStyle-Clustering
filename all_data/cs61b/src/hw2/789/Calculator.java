import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eList;
    public int eListLength;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (x < 0) {
            if (invertNumber(x) > y) {
                return invertNumber(add(invertNumber(x), invertNumber(y)));
            }
            else if (invertNumber(x) < y) {
                return increaseByStep(y, x);
            }
            else {
                return 0;
            }
        }
        else if (y < 0) {
            if (invertNumber(y) > x) {
                return invertNumber(add(invertNumber(x), invertNumber(y)));
            }
            else if (invertNumber(y) < x) {
                // return decreaseByStep(x, invertNumber(y));
                return increaseByStep(x, y);
            }
            else {
                return 0;
            }
        }
        else if (x == 0) {
            return y;
        }
        else if (y == 0) {
            return x;
        }
        else {
            return increaseByStep(x, y);
        }
    }

    private int increaseByStep(int x, int y) {
        int z;
        while (x != 0) {
            z = x & y;
            y = x ^ y;
            z = z << 1;
            x = z;
        }
        return y;
    }

    private int invertNumber(int x) {
        return increaseByStep(~x, 1);
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
        if ( (x < 0) && (y < 0) ) {
            return multiply(invertNumber(x), invertNumber(y));
        }
        else if (x < 0) {
            return invertNumber(multiply(invertNumber(x), y));
        }
        else if (y < 0) {
            return invertNumber(multiply(x, invertNumber(y)));
        }
        else {
            int total  = 0;
            for (int i = 0; i < y; i++) {
                total = add(total, x);
            }
            return total;
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
        eList = new EquationList(equation, result, eList);
        eListLength = eListLength + 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(eListLength);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointer = eList;
        for (int i = 0; i < n; i++) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eList = eList.next;
        eListLength = eListLength - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (eList.next != null) {
            eList = eList.next;
        }
        eListLength = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        EquationList pointer = eList;
        for (int i = 0; i < eListLength; i++) {
            total = add(total, pointer.result);
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
        int total = 1;
        EquationList pointer = eList;
        for (int i = 0; i < eListLength; i++) {
            total = multiply(total, pointer.result);
            pointer = pointer.next;
        }
        return total;
    }
}
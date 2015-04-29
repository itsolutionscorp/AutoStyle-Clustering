import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    //for task 4:
    public EquationList nextone = null;
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
        int xored = x ^ y;
        int carryover = ((x & y) << 1);
        if (carryover == 0) {
            return xored;
        } else {
            return add(xored, carryover);
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
        // YOUR CODE HERE
        if (y == 0 || x == 0) {
            return 0;
        } else {
            int xTwoExponent = 0;
            int total = 0;
            while (y != 0) {
                int yholder = y;
                y = y >>> 1;
                if ((y << 1) < yholder) {
                    total = add(total, x << xTwoExponent);
                    }
                xTwoExponent = add(xTwoExponent, 1); 
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
        // YOUR CODE HERE
        nextone = new EquationList(equation, result, nextone);
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
        EquationList steps;
        int length = 0;
        steps = nextone;
        while (steps != null) {
            steps = steps.next;
            length += 1;
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
        // YOUR CODE HERE
    EquationList stepper;
    int counter = 0;
    stepper = nextone;
    while (counter != n) {
        System.out.println(stepper.equation + " = " + stepper.result);
        counter = counter + 1;
        if (stepper.next == null){
            break;
            }
        stepper = stepper.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        nextone = nextone.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        nextone.next = null;
        nextone = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList steps = nextone;
        int totality = 0;
        while (steps != null) {
            totality += steps.result;
            steps = steps.next;
        }
        return totality; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList steps;
        int totality = 1;
        steps = nextone;
        while (steps != null) {
            if (steps.result == 0) {
                return 0;
            }
            totality *= steps.result;
            steps = steps.next;
        }
        return totality;
    }
}
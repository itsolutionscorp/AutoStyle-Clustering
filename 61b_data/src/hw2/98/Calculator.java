import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
    private int total;

    public Calculator() {
        total = 0;
        history = null;
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int result = x ^ y;
        int shifts = x & y;
        // Bit magics sum the new shifts with the new result.
        // Recursively manage new shifts and results through add.
        // Stop once no more shifts required and a simple XOR returns
        // the right answer.
        if (shifts == 0) {
            return result;
        }
        else {
            return add(result, shifts << 1);
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
        if (x == 0) {
            return 0;
        }
        if (y == 0) {
            return 0;
        }
        // Bit magics increase x by one power and y down by one power.
        // Similar to the idea of integer long multiplication.
        if ((1 & y) != 0) { // Check if the first bit is 1
            return add(x, multiply(x << 1, y >> 1)); 
        }
        else {
            return multiply(x << 1, y >> 1);
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
        // Adding to the head of the list
        EquationList temp = new EquationList(equation, result, null);
        temp.next = history;
        history = temp;
        total++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(total);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList current = history;
        for (int i = 0; i < n; i++) {
            if (current != null) {
                System.out.println(current.equation + " = " + current.result);
                if(current.next == null) {
                    return;
                }
                current = current.next;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
            total--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null; // Thank you based garbage collector
        total = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int currentSum = 0;
        EquationList ptr = history;
        while(ptr != null) {
            currentSum = add(currentSum, ptr.result);
            ptr = ptr.next;
        }
        return currentSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (history == null) {
            return 0;
        }
        int currentProd = 1;
        EquationList ptr = history;
        while(ptr != null) {
            currentProd = multiply(currentProd, ptr.result);
            ptr = ptr.next;
        }
        return currentProd;
    }
}

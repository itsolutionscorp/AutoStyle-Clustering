import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList entries;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
    int carryString = x & y;
    int xorString = x ^ y;
    int newCarryString;
    int newXorString;

    while (carryString != 0) {
        newCarryString = (carryString << 1) & xorString;
        newXorString = (carryString << 1) ^ xorString;
        carryString = newCarryString;
        xorString = newXorString;
    }
    return xorString;
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
        if(x == 0 || y == 0) {
            return 0;
        }

        int digit;
        int total = 0;  //desired product
        int nthPower = 0;  //current digit's 2^N power
        while(y != 0) {
            digit = y & 1;  //isolates last digit in bit string

            if(digit == 1) {
                total = add(total, x << nthPower);
            }

            y = y >>> 1;
            nthPower += 1;
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
        // YOUR CODE HERE
        entries = new EquationList(equation, result, entries);
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
        EquationList history = entries;

        while(history != null) {
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
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
        if(entries == null) {
            return;
        }

        EquationList history = entries;
        int desired_history = 1;

        while(desired_history < n) {
            history = history.next;
            desired_history += 1;
        }

        System.out.println(history.equation + " = " + history.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        entries = entries.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while(entries != null) {
            entries = entries.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList history = entries;
        int total = 0;  //desired cumulative sum

        while(history != null) {
            total = add(total, history.result); 
            history = history.next;
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
        // YOUR CODE HERE
        EquationList history = entries;
        int total = 1;

        while(history != null) {
            total = multiply(total, history.result);
            history = history.next;
        }
        
        return total;
    }
}
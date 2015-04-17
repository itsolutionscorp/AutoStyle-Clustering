import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList storedHistory;
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
        int carryOver = x & y;
        int result = x ^ y;
        while (carryOver!= 0){
            int shiftCarryOver = carryOver << 1;
            carryOver = shiftCarryOver & result;
            result = shiftCarryOver ^ result;
        }
        return result;
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
        int result = 0;
        while (y != 0){
            if ((y & 1) != 0){
                result = this.add(x, result);
            }
            x = x << 1;
            y = y >>> 1;
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
        // YOUR CODE HERE//
            if (storedHistory == null){
                storedHistory = new EquationList(equation, result, null);
            }
            else{
                storedHistory = new EquationList(equation, result, storedHistory);
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
        // YOUR CODE HERE
        EquationList copyOfHistory = storedHistory;
        int counter = 0;
        while (copyOfHistory != null){
            counter += 1;
            copyOfHistory = copyOfHistory.next;
        }
        this.printHistory(counter);
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
        EquationList copyOfHistory = storedHistory;
        while (n>0){
            System.out.println(copyOfHistory.equation + " = " + String.valueOf(copyOfHistory.result));
            copyOfHistory = copyOfHistory.next;
            n = n - 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        storedHistory = storedHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (storedHistory!= null){
            storedHistory = storedHistory.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE 
        int sum = 0;
        EquationList copyOfHistory = storedHistory;
        if (storedHistory == null){
            return sum;
        }
        while (copyOfHistory != null){
            sum = this.add(sum, copyOfHistory.result);
            copyOfHistory = copyOfHistory.next;
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
        // YOUR CODE HERE
        int product = 1;
        EquationList copyOfHistory = storedHistory;
        if (storedHistory == null){
            return product;
        }
        while (copyOfHistory != null){
            product = this.multiply(product, copyOfHistory.result);
            copyOfHistory = copyOfHistory.next;
        }
        return product;
    }
}
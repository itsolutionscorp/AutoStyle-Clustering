import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equationHistory;
    public EquationList copyList;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carriedOver = x & y;
        int exOR = x ^ y;
        int newCarried;
        while (carriedOver != 0){
            newCarried = carriedOver << 1;
            carriedOver = exOR & newCarried;
            exOR = exOR ^ newCarried;
        } 
        return exOR;
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
    int ans = 0;
    while (y != 0){
            if ((y & 1) == 1) {
                ans = add(ans, x);
            }
        x = x << 1;
        y = y >>> 1;
        }
    return ans;
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
        EquationList newEqn = new EquationList(equation, result, equationHistory);
        equationHistory = newEqn;
        //System.out.println(equation + ' = ' + result);
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
        copyList = equationHistory;
        while (equationHistory != null){
            String eqnString = equationHistory.equation;
            int eqnResult = equationHistory.result;
            System.out.println(eqnString + " = " + eqnResult);
            equationHistory = equationHistory.next;
        }
        equationHistory = copyList;
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
        copyList = equationHistory;
        while (n != 0){
            String eqnString = equationHistory.equation;
            int eqnResult = equationHistory.result;
            System.out.println(eqnString + " = " + eqnResult);
            equationHistory = equationHistory.next;
            n -= 1;
        }
        equationHistory = copyList;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        copyList = equationHistory;
        int cumSum = 0;
        while (equationHistory != null){
            cumSum = add(cumSum, equationHistory.result);
            equationHistory = equationHistory.next;
        }
        equationHistory = copyList;
        return cumSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        copyList = equationHistory;
        int cumProduct = 1;
        while (equationHistory != null){
            cumProduct = multiply(cumProduct, equationHistory.result);
            equationHistory = equationHistory.next;
        }
        equationHistory = copyList;
        return cumProduct;
    }
}
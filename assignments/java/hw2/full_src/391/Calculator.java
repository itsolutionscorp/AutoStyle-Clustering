import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList test = null;  
    public int startSum = 0; 
    public int startPdt = 1; 

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
        int ans = x ^ y; 
        int tempStore = (x & y);  
        /* top is like the carry */ 
        int top = (tempStore << 1); 
        if (top != 0){
            return add(ans, top); 
        }
        return ans; 
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
            /* in the end, want y to be reduced to 1 */ 
            if ((y & 1) != 0){
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

        EquationList eqnList = new EquationList(equation, result, test); 
        test = eqnList; 
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
        EquationList test1 = test; 
        while (test1 != null){
            System.out.println(test1.equation + " = " + test1.result); 
            test1 = test1.next; 
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
    int i = 0;
    EquationList test2 = test; 
    while (i < n){
        System.out.println(test2.equation + " = " + test2.result); 
        test2 = test2.next; 
        i = i + 1; 
    } 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        test.equation = test.next.equation; 
        test.result = test.next.result; 
        test.next = test.next.next; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        test = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/

    public int cumulativeSum() {
        // YOUR CODE HERE
        while (test != null){
           startSum = test.result + startSum; 
            test = test.next; 
        } 
        return startSum; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
         while (test != null){
           startPdt = test.result * startPdt; 
            test = test.next; 
        }
        return startPdt; 
    }
}
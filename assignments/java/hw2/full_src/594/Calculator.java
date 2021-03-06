import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     * 1 = 0001   1001
     * 2 = 0010   0110  
     * 3 = 0011
     **/
    public int add(int x, int y) {
        int and;
        int xor;
        int tmp;
        and = x & y; // 1 & 1 = 0001
        xor = x ^ y; // 1 ^ 1 = 0000
        while (and != 0) {
            and <<= 1; // and = 0010
            tmp = xor ^ and; // temp = 0000 ^ 0010 = 0010
            and &= xor; // and = and & xor = 0010 & 0001 = 0000
            xor = tmp; // xor = 0010
        }
        return xor;
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
        int product = 0;
        while (x != 0) {
            if ((x & 1) == 1) {
                product = add(product, y);
            }
            y <<= 1;
            x >>>= 1;
        }
        return product;
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
        history = new EquationList(equation, result, history);
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
        EquationList tempHistory = this.history;
        while (tempHistory != null) {
            System.out.println(tempHistory.equation + " = " + tempHistory.result);
            tempHistory = tempHistory.next;
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
        EquationList tempHistory = this.history;
        int i = 0;
        while (i < n && tempHistory != null) {
            System.out.println(tempHistory.equation + " = " + tempHistory.result);
            tempHistory = tempHistory.next;
            i++;
        }     
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
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
        EquationList tempHistory = this.history;
        int totalSum = 0;
        while (tempHistory != null) {
            totalSum = add(totalSum, tempHistory.result); 
            tempHistory = tempHistory.next;
        }
        return totalSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList tempHistory = this.history;
        int totalProduct = 1;
        while (tempHistory != null) {
            totalProduct = multiply(totalProduct, tempHistory.result); 
            tempHistory = tempHistory.next;
        }
        return totalProduct;
    }
}
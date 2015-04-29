import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList totalHistory;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public void Calculator() {
        totalHistory = null;
    }

    public int add(int x, int y) {
        // YOUR CODE HERE
        // boolean carry = false;
        // int result = 0;
        // int i = 0;
        
        // while (x != 0 || y != 0) {
            
        //     if (((x ^ y) & 1) == 1) {
        //         if (!carry) {
        //             result = (1 << i) | result;
        //         } 
        //     } else if (((x & y) & 1) == 1) {
        //         if(carry) {
        //             result = (1 << i) | result;
        //         } else {
        //             carry = true; 
        //         }
        //     } else {
        //         if(carry) {
        //             result = (1 << i) | result;
        //             carry = false;
        //         }
        //     }
        //     x = x >>> 1;
        //     y = y >>> 1;
        //     i++;
        // }

        // if (carry) {
        //     result = (1 << i) | result;
        // }
        // return result;
        boolean carry = false;
        int result = 0;
        int digits = 32;
        
        for(int i = 0; i < digits; i++) {
            
            if (((x ^ y) & (1 << i)) == (1 << i)) {
                if (!carry) {
                    result = (1 << i) | result;
                } 
            } else if (((x & y) & (1 << i)) == (1 << i)) {
                if(carry) {
                    result = (1 << i) | result;
                } else {
                    carry = true; 
                }
            } else {
                if(carry) {
                    result = (1 << i) | result;
                    carry = false;
                }
            }
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
        int digits = 32;

        for(int i = 0; i < digits; i ++) {
            if((x & (1 << i)) == (1 << i)) {
                result = add(result, y << i);
            }
        } 
              
        return result;
        // while(x != 0) {
        //     if((x & (1 << i)) == (1 << i)) {
        //         result = add(result, y << i);
        //     }
        // } 
              
        // return result;
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
        totalHistory = new EquationList(equation, result, totalHistory);
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
        printHistory(Integer.MAX_VALUE);
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
        EquationList history = totalHistory;

        while(history != null && n > 0) {
            System.out.println(history.equation + " = " + history.result);
            n -= 1;
            history = history.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        totalHistory = totalHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        totalHistory = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList history = totalHistory;
        int sum = 0;

        while (history != null) {
            sum += history.result;
            history = history.next;
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
        EquationList history = totalHistory;
        int product = 1;

        while (history != null) {
            product *= history.result;
            history = history.next;
        }
        return product;
    }
}
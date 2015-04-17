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
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        while (y!=0) {
            int carryOver = x & y;
            x = x ^ y;
            y = carryOver << 1;
        }
        return x;
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
        int product = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                product = add(product, x);
            }
            x = x << 1;
            y = y >>> 1;
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else {
            history = new EquationList(equation, result, history);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    //helper length fuction
    public int historyLength(EquationList history) {
        if (history == null) {
            return 0;
        }
        return 1 + historyLength(history.next);
    }


    public void printAllHistory() {
        int l = historyLength(this.history);
        printHistory(l);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
      int k = 0;
      EquationList h = history;
      while (k < n) {
        System.out.println(h.equation + " = " + h.result);
        h = h.next;
        k += 1;

      }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = new EquationList(history.next.equation, 
                                   history.next.result, history.next.next);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/

    public int cumSumHelper(EquationList h) {
        while (h != null) {
            return h.result + cumSumHelper(h.next);
        }
        return 0;
        
    }



    public int cumulativeSum() {
        int sum = 0;
        if (history != null) {
            sum = history.result + cumSumHelper(history.next);
        }
        return sum;
        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/

    public int cumProductHelper(EquationList h) {
        while (h != null) {
            return h.result * cumProductHelper(h.next);
        }
        return 1;
    }


    public int cumulativeProduct() {
        // YOUR CODE HERE
        int product = 1;
        if (history != null) {
            product = history.result * cumProductHelper(history.next);
        }
        return product;
    }
}








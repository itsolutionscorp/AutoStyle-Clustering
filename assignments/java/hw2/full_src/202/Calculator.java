import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
    public EquationList history_ptr;

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
        if (y == 0){
            return x;
        }
        int temp = x;
        x = x ^ y;
        y = (temp & y) << 1;
        return add(x, y);
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
        if (x == 0 || y == 0){
            return 0;
        } else if (x < 0 && y < 0){
            x = add((~x), 1);
            y = add((~y), 1);
            product = helper(x, y);
        } else if (y < 0){
            product = helper(y, x);
        } else {
            product = helper(x, y);
        }
        return product;
    }
            

    private int helper(int num, int count){
        int product = 0;
        while (count != 0) {
            product = add(product, num);
            count--;
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
        if (history == null){
            history = new EquationList(equation, result, null);
            history_ptr = history;
        } else {
            // history_ptr.next = new EquationList(equation, result, null);
            // history_ptr = history_ptr.next;
            history = new EquationList(equation, result, history_ptr);
            history_ptr = history;
        }
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() { //Should be calling printHistory
        // YOUR CODE HERE
        
        // EquationList tracker = history;
        // while (tracker != null){
        //     System.out.println(tracker.equation + " = " + tracker.result);
        //     tracker = history_ptr.next; }
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
        EquationList tracker = history;
        int count = 0;
        while ((count < n) && (tracker != null)){
            System.out.println(tracker.equation + " = " + tracker.result);
            tracker = tracker.next;
            count++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
        history_ptr = history;
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
        int total = 0;
        EquationList tracker = history;
        while (tracker != null){
            total = total + tracker.result;
            tracker = tracker.next;
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
        int total = 1;
        EquationList tracker = history;
        while (tracker != null){
            total = total * tracker.result;
            tracker = tracker.next;
        }
        return total;
    }
}
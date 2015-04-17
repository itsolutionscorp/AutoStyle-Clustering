import list.EquationList;

public class Calculator{
    EquationList entropy = null;
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y){
        int base = (x ^ y);
        int carry = (x & y);
        if(carry == 0){
            return base;
        }
        else{
            return add((carry << 1), base);
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
       public  int multiply(int x, int y){
        if((x < 0) && (y > 0)){
            return multiply(y, x);
        }
        if((y < 0) && (x < 0)) {
            return multiply(add((~y), 1), add((~x), 1));
        }
        int tracker = 0;
        if(x == 0){
            return tracker;
        }
        else{
            if((x & 1) == 1){
            tracker = add(tracker, y);
            return add(tracker, multiply((x >> 1), (y << 1)));
            }
            else{
                return add(tracker, multiply((x >> 1), (y << 1)));
            }
            
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
        EquationList one = new EquationList(equation, result, entropy);
        entropy = one;
        // YOUR CODE HERE
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList gamma = entropy;
        while (gamma != null) {
           System.out.println(gamma.equation + " " + "=" + " " + Integer.toString(gamma.result));
           gamma = gamma.next;
        }
        // YOUR CODE HERE
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList gamma = entropy;
        while ((gamma != null) && (n > 0)) {
            System.out.println(gamma.equation + "" + "=" + Integer.toString(gamma.result));
            gamma = gamma.next;
            n -= 1;
        }
        // YOUR CODE HERE
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        entropy = entropy.next;
        // YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (entropy != null) {
            entropy = entropy.next;
        }
        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int tracker = 0;
        EquationList gamma = entropy;
        while (gamma != null) {
            tracker += gamma.result;
            gamma = gamma.next; 
        }
        return tracker;        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int tracker = 1;
        EquationList gamma = entropy;
        while (gamma != null){
            tracker *= gamma.result;
            gamma = gamma.next;
        }
        return tracker;
        // YOUR CODE HERE
        //return -1;
    }

}


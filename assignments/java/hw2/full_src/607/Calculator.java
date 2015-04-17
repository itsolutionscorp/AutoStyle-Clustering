import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList hist = null;
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
        int original = x ^ y;
        for (int carry = (x & y) << 1; carry != 0; carry <<= 1) {
            int temp = original ^ carry;
            carry = carry & original;
            original = temp;
        }
        return original;
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
        int sum = 0;
        int original;
        int shifter;
        if ((x < 0) && (y < 0)) {
            original = add(~x, 1);
            shifter = add(~y, 1);
        }
        else if (y < 0) {
            original = y;
            shifter = x;
        }
        else {
            original = x;
            shifter = y;
        }
        while (shifter != 0) {
            if ((shifter & 1) != 0) {
                sum = add(sum, original);
            }
            original <<= 1;
            shifter >>= 1;
        }
        return sum;
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
        hist = new EquationList(equation, result, hist);
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
        EquationList currHead = hist;
        int i = 1;
        while (currHead != null) {
           printHistory(i);
           currHead = currHead.next;
           i += 1;
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
        EquationList currHead = hist;
        for (int i = 1; i < n; i += 1) {
            currHead = currHead.next;
        }
        System.out.println(currHead.equation + " = " + currHead.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        hist = hist.next;
    }

    /**
     * TAShist = null;K 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList curr = hist;
        int sum = 0;
        while (curr != null) {
            sum += curr.result;
            curr = curr.next;
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
        EquationList curr = hist;
        int product = 1;
        while (curr != null) {
            product *= curr.result;
            curr = curr.next;
        }
        return product;
    }
}



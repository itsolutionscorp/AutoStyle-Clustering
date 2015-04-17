import list.EquationList;

public class Calculator {

    public EquationList history;

    public Calculator() {
        history = null;
    }

    // YOU MAY WISH TO ADD SOME FIELDS


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        int flip = x ^ y, and = x & y, temp;
        while (and != 0) {
            and <<= 1;
            temp = flip ^ and;
            and &= flip;
            flip = temp;
        }
        return flip;
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

        int result = 0;
        while (y != 0)
        {
            if ((y & 1) != 0)    
                result = add(result, x); 
            x <<= 1;
            y >>>= 1;
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

        EquationList temp = new EquationList(equation, result, history);
        history = temp;

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

        EquationList temp = history;
        while (temp != null) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
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

        EquationList temp = history;
        while (n > 0) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n--;
        }

        // YOUR CODE HERE

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {

        if (history != null)
            history = history.next;
        // YOUR CODE HERE

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {

        history = null;

        // YOUR CODE HERE

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        int sum = 0;
        EquationList temp = history;
        while (temp != null) {
            sum += temp.result;
            temp = temp.next;
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

        int prod = 1;
        EquationList temp = history;
        while (temp != null) {
            prod *= temp.result;
            temp = temp.next;
        }
        return prod;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    
    public EquationList copy, prevEqs, empty;

    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0) {

            // Figure out the columns where you have two ones:
            int carryOvers = x & y;

            // Add the columns where you have one one:
            x = x ^ y;

            // Change y to what you have to carry over.
            // And move them left to carry them over:
            y = carryOvers << 1;
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
        
        if (y == 1){
            return x;
        } 

        else if ((y & 1) == 1) {
            return x + multiply(x << 1, y >> 1);
        }


        // Shift Y >> until the last bit is 1.
            // Y & 1 gives last bit.
        int index = 0;

        while ((y & 1) != 1) {
            y = y >> 1;
            index += 1;
        }

        // Shift X << the number of times Y was shifted.
        x = x << index;

        if (y > 1) {
            return multiply(x, y);
        } else {
            return x;
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
        // YOUR CODE HERE

        prevEqs = new EquationList(equation, result, prevEqs);

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
        int index = 0;
        copy = prevEqs;

        while (copy != null) {
            index += 1;
            copy = copy.next;
        }

        printHistory(index);

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
        copy = prevEqs;
        int index = 0;

        while (index < n) {

            System.out.println(copy.equation + " = " + Integer.toString(copy.result));
            copy = copy.next;
            index += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        prevEqs = prevEqs.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        prevEqs = empty;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        copy = prevEqs;

        while (copy != null) {
            sum += copy.result;
            copy = copy.next;
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
        int product;
        copy = prevEqs;

        if (copy != null) {
            product = copy.result;
            copy = copy.next;
        } else {
            return 0;
        }
        
        while (copy != null) {
            product *= copy.result;
            copy = copy.next;
        }
        return product;
    }
}
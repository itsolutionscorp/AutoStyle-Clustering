import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null;

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

        int xor = x ^ y;
        int and = x & y;

        if (and == 0) {
            return xor;
        } 

        and = and << 1;
        return add(xor, and);
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
        int and = 0;
        int checker = 1;
        int adder = x;
        int condition = y;

        while (condition != 0) {

            and = checker & y;

            if (and != 0) {
                for (int i = 1; i != and; i = i << 1) {
                    adder = adder << 1;
                }

                sum = add(sum, adder);
            }
            adder = x;
            checker = checker << 1;
            condition = condition >>> 1;
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
        this.history = new EquationList(equation, result, history);

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

        if (history == null) {
            
        } else {
            EquationList tentative = this.history;

            while (tentative != null) {
                System.out.println(tentative.equation + " = " + tentative.result);
                tentative = tentative.next;
            }
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

        int counter = 0;
        if (history == null) {

            System.out.println("No history available.");

        } else {

            EquationList tentative = history;

            while (counter < n) {
                System.out.println(tentative.equation + " = " + tentative.result);
                tentative = tentative.next;
                counter += 1;
            }
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE

        this.history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE

        this.history = null;

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

        if (this.history == null) {
            return 0;
        } else {

            EquationList tentative = this.history;


            while (tentative != null) {

                sum += tentative.result;
                tentative = tentative.next;

            }

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
        int product = 1;

        if (this.history == null) {
            return 0;
        } else {

            EquationList tentative = this.history;


            while (tentative != null) {

                product *= tentative.result;
                tentative = tentative.next;

            }

        }

        return product;
    }
}
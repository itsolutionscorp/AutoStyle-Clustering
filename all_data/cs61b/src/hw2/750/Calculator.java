import list.EquationList;

public class Calculator {
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
        // YOUR CODE HERE
        int sum = (x ^ y);
        int z = (x & y) << 1;
        while (z != 0) {
            int h = sum;
            sum = (sum ^ z);
            z = (h & z) << 1;
        }
        return sum;
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
        if (x == 0 || y == 0) {
            return 0;
        }
        int product = 0;
        int v = x;
        int counter = 0;
        int z = y;
        int w = 0;
        while (z != 0) {
            int h = (1 & z);
            if (h != 0) {
                w = v << counter;
                product = add(product, w);
            }
            z = (z >>> 1);
            counter = counter + 1;
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

    EquationList history = new EquationList();
    int input_number = 0;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (history.equation == "No history") {
            history = new EquationList(equation, result);
            input_number = input_number + 1;
        }
        else {
            history = new EquationList(equation, result, history);
            input_number = input_number + 1;
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        //printHistory(2147483647);
        printHistory(input_number);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int counter = n;
        EquationList ptr = history;
        boolean mrBoolean = true;
        while (counter != 0 && mrBoolean && (history.equation != "No history")) {
            System.out.println(ptr.equation + " = " + Integer.toString(ptr.result));
            if (ptr.next == null) {
                mrBoolean = false;
            }
            ptr = ptr.next;
            counter = counter - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
            if (input_number > 0) {
            input_number = input_number - 1;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = new EquationList();
        input_number = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int counter = input_number;
        EquationList ptr = history;
        int sum = 0;
        while (counter != 0) {
            sum = add(ptr.result, sum);
            ptr = ptr.next;
            counter = counter - 1;
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
        int counter = input_number;
        EquationList ptr = history;
        int product = 1;
        while (counter != 0) {
            product = multiply(ptr.result, product);
            ptr = ptr.next;
            counter = counter - 1;
        }
        return product;
    }
}
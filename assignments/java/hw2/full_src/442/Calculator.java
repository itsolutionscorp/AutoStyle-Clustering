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
    EquationList lst;
    int length;
    public int add(int x, int y) {
        // YOUR CODE HERE
        boolean carry_over = false;
        int counter = 0;
        int mask, xbit, ybit, targbit;
        int result = 0;
        while (counter < 32) {
            mask = 1 << counter;
            xbit = x & mask;
            ybit = y & mask;
            targbit = xbit ^ ybit;
            if (carry_over) {
                targbit = targbit ^ mask;
            }
            if (xbit != 0 && ybit != 0) {
                carry_over = true;
            } else if (carry_over && (xbit != 0 || ybit != 0)) {
                carry_over = true;
            } else {
                carry_over = false;
            }
            result = result | targbit;
            counter = counter + 1;
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
        int counter = 0;
        int mask, ybit;
        while (counter < 32) {
            mask = 1 << counter;
            ybit = mask & y;
            if (ybit != 0) {
                result = add(result, multpow2(x, counter));
            }
            counter = counter + 1;
        }
        return result;
    }

    private int multpow2(int x, int exp) {
        return x << exp;
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
        lst = new EquationList(equation, result, lst);
        length = length + 1;
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
        printHistory(length);
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
        EquationList runner = lst;
        while (runner != null && n > 0) {
            System.out.println(runner.equation + " = " + runner.result);
            runner = runner.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        lst = lst.next;
        length = length - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        lst = null;
        length = 0;
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
        EquationList runner = lst;
        while (runner != null) {
            sum = sum + runner.result;
            runner = runner.next;
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
        int prod = 1;
        EquationList runner = lst;
        while (runner != null) {
            prod = prod * runner.result;
            runner = runner.next;
        }
        return prod;
    }
}
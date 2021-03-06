import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList historyList;

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
        int carry = x & y;
        if (carry != 0) {
            return add((x ^ y), (carry << 1));
        } else {
            return x ^ y;
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
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        /*if (y == 1) {
            return x;
        } else if (y == -1) {
            return -x;
        } else if (y == 0 || x == 0) {
            return 0;
        } else if (y != ((y >> 1) << 1))) {
            return add(multiply(add(y, -(y >> 1)), x) ,multiply(x, (y >> 1)));
        } else {
            return multiply((x << 1), (y >> 1));
        }*/
        if (y == 0 || x == 0) {
            return 0;
        } else if ((y >> 1) == 0) {
            return x;
        } else if (y != ((y >> 1) << 1)) {
            return add(x ,multiply(x << 1, y >> 1));
        } else {
            return multiply((x << 1), (y >> 1));
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
        historyList = new EquationList(equation, result, historyList);
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
        EquationList curr = historyList;
        while (curr != null) {
            System.out.println(curr.equation + " " + "=" + " " + curr.result);
            curr = curr.next;
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
        EquationList curr = historyList;
        int i = 0;
        while (i < n) {
            System.out.println(curr.equation + " " + "=" + " " + curr.result);
            curr = curr.next;
            i ++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        historyList = historyList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        historyList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList curr = historyList;
        int sum = 0;
        while (curr != null) {
            sum = sum + curr.result;
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
        EquationList curr = historyList;
        int product = 1;
        while (curr != null) {
            product = product * curr.result;
            curr = curr.next;
        }
        return product;
    }
}
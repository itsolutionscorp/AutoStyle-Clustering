import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
    public int numEquations = 0;
    public EquationList reset = history;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    //return the yth bit of x
    public int add(int x, int y) {
        // YOUR CODE HERE
        while (y != 0) {
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
        int oddLeftovers = 0;
        if (x == 0 || y == 0) {
            return 0;
        } else if (y > 0) {
            while (y != 1) {
                if ((y & 1) != 0) {
                    oddLeftovers = add(oddLeftovers, x);
                }
                x = x << 1;
                y = y >> 1;
            }
            return add(x, oddLeftovers);
        } else {
            y = add(1, (~ y));
            while (y != 1) {
                if ((y & 1) != 0) {
                    oddLeftovers = add(oddLeftovers, x);
                }
                x = x << 1;
                y = y >> 1;
            }
            return add(1, (~ add(oddLeftovers, x)));
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
        history = new EquationList(equation, result, history);
        numEquations += 1;
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
        EquationList p = history;
        while (p != null) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
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
        if (n > numEquations) {
            printAllHistory();
        } else {
            EquationList p = history;
            for (int i = 0; i < n; i++) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
            }
        }
}  
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history == null) {
            return;
        }
        history = history.next;
        numEquations -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = reset;
        numEquations = 0;
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
        EquationList p = history;
        while(p != null) {
            sum = add(sum, p.result);
            p = p.next;
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
        EquationList p = history;
        while(p != null) {
            product = multiply(product, p.result);
            p = p.next;
        }
        return product;
    }
}




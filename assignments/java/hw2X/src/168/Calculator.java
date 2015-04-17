import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = new EquationList("End of history.", 0, null);

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
        int x1 = x;
        int y1 = y;
        int count = 31;
        int k = 0; 
        int base = 0;
        while (count > 0) {
            x1 = x << count;
            y1 = y << count;
            x1 = x1 >>> 31;
            y1 = y1 >>> 31;
            x1 = x1 << 31;
            y1 = y1 << 31;
            x1 = x1 >>> count;
            y1 = y1 >>> count;
            int result = x1 ^ y1;
            int result1 = result << count;
            result1 = result1 >>> 31;

            if (result1 == 0) {
                if (x1 != 0) {
                    if (k == 1) {
                       result = result | (((1 << 31) >>> count)); 
                    }
                    k = 1;
                }
                else if (k == 1) {
                    result = result | (((1 << 31) >>> count));
                    k = 0;
                }            
            }
            else {
                if (k == 1) {
                    k = 1;
                    result = 0;
                }
                else {
                    k = 0;
                }
            }

            base = base | result;
            count -= 1;
        }
        if (((x < 0) && (add(~x, 1) > y)) || 
            ((y < 0) && (add(~y, 1) > x))) {
            return (base | (1 << 31)); 
        }
        return base;
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
        if ((x >= 0) && (y < 0)) {
            int a = y;
            y = x;
            x = a;
        }
        else if ((x < 0) && (y < 0)) {
            x = add(~x, 1);
            y = add(~y, 1);
        }

        int count = y;
        int total = 0;
        while (count > 0) {
            total = add(total, x);
            count = add(count, add(~1, 1));
        }
        return total;
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
        int count = 0;
        EquationList clone = history;
        while (clone.next != null) {
            count += 1;
            clone = clone.next;
        }
        printHistory(count);
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
        EquationList clone = history;
        while (n > 0) {
            System.out.println(clone.equation + " = " + clone.result);
            clone = clone.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (history.next != null) {
            history = history.next;
        }
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
        EquationList clone = history;
        while (clone.next != null) {
            sum += clone.result;
            clone = clone.next;
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
        EquationList clone = history;
        while (clone.next != null) {
            product *= clone.result;
            clone = clone.next;
        }
        return product;
    }
}
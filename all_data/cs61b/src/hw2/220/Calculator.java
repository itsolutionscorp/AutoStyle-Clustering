import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    
    private EquationList history;
    private int total;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = 0;
        int carry = 0;
        int digitX;
        int digitY;
        int count = -1;
        int size = Integer.SIZE - 1;
        while (count != 0) {
            sum >>>= 1;
            digitX = x & 1;
            digitY = y & 1;
            sum |= (digitX ^ digitY ^ carry) << size;
           // System.out.format("%d %d %d %d\n", digitX, digitY, carry, sum);
            carry = (digitX & digitY) | (digitY & carry) | (digitX & carry);
            x >>= 1;
            y >>= 1;
            count >>>= 1;
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
        int count = -1;
        int product = 0;
        int digitY;
        while (count != 0) {
            if ((y & 1) == 1) {
                product = add(product, x);
            }
            y >>= 1;
            x <<= 1;
            count >>>= 1;
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
    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history);
        total++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(total);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList current = history;
        while (current != null && n-- > 0) {
            System.out.format("%s = %d%n", current.equation, current.result);
            current = current.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
        total--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        total = 0;
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int result = 0;
        EquationList current = history;
        while (current != null) {
            result = add(result, current.result);
            current = current.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int result = 1;
        EquationList current = history;
        while (current != null) {
            result = multiply(result, current.result);
            current = current.next;
        }
        return result;
    }
}

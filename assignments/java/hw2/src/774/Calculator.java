import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equations;

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
        int addZeroOnes = x ^ y;
        int carryColumns = x & y;
        if (carryColumns == 0) {
            return addZeroOnes;
        }
        return add(addZeroOnes, (carryColumns << 1));
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
        while (y != 0) {
            if ((y & 1) != 0) {
                sum = add(sum, x);
            }
            x = x << 1;
            y = y >>> 1;
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
        equations = new EquationList(equation, result, equations);
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
        if (equations == null) {
            return;
        }
        EquationList eqnCopy = new EquationList(equations.equation, equations.result, equations.next);
        int count = 1;
        while (eqnCopy.next != null) {
            count += 1;
            eqnCopy = eqnCopy.next;
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
        EquationList eqnsCopy = new EquationList(equations.equation, equations.result, equations.next);
        while (n > 0 && eqnsCopy != null) {
            System.out.println(eqnsCopy.equation + " = " + eqnsCopy.result);
            if (eqnsCopy.next != null) {
                eqnsCopy = eqnsCopy.next;
            }
            else {
                return;
            }
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equations = null;
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
        if (equations == null) {
            return sum;
        }
        EquationList eqnsCopy = new EquationList(equations.equation, equations.result, equations.next);
        while (eqnsCopy.next != null) {
            sum += eqnsCopy.result;
            eqnsCopy = eqnsCopy.next;
        }
        return sum + eqnsCopy.result;
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
        if (equations == null) {
            return product;
        }
        EquationList eqnsCopy = new EquationList(equations.equation, equations.result, equations.next);
        while (eqnsCopy.next != null) {
            product *= eqnsCopy.result;
            eqnsCopy = eqnsCopy.next;
        }
        return product * eqnsCopy.result;
    }
}
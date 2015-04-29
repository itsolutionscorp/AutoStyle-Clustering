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
        if (x == 0) {
            return y;
        } else if (y == 0) {
            return x;
        } else {
            if ((x & y) == 0) {
                return x | y;
            } else {
                return add(x ^ y, (x & y) << 1);
            }
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
        if (x == 0 || y == 0) {
            return 0;
        } else if ((x < 0) && (y > 0)) {
            int a = multiply(add(~x, 1), y);
            return add(~a, 1);
        } else if ((x > 0) && (y < 0)) {
            int b = multiply(x, add(~y, 1));
            return add(~b, 1);
         } else if ((x < 0) && (y < 0)) {
            return multiply(add(~x, 1), add(~y, 1));
        } else {
            int i = 0;
            while (y != 0) {
                if ((y & 1) != 0) {
                    i = add(i, x);}
                x = x << 1;
                y = y >> 1;
            }
            return i;
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
    public EquationList equations;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList equations2 = new EquationList(equation, result, equations); 
        equations = equations2;
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
        printHistory(Integer.MAX_VALUE);
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
        int i = 0;
        EquationList a = equations;
        while (i < n  && (a != null)) {
            System.out.println(a.equation + " = " + String.valueOf(a.result));
            a = a.next;
            i = i + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        //if (equations.next == null) {
        //    equations = null;
        //} else if (equations.next.next == null) {
        //    equations.next = null;
        //} else {
        //    equations = equations.next;
        //}
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
        //if (equations == null) {
        //    return 0;
        //} else {
        //    int a = equations.result;
        //    equations = equations.next;
        //    return a + cumulativeSum(); 
        //}
        EquationList a = equations;
        int sum = 0;
        while (a != null) {        
            sum = this.add(sum, a.result);
            a = a.next;
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
        //if (equations.next == null) {
        //    return equations.result;
        //} else {
        //    int a = equations.result;
        //    equations = equations.next;
        //    return a * cumulativeProduct(); 
        //}
        EquationList a = equations;
        int product = 1;
        while (a != null) {        
            product = this.multiply(product, a.result);
            a = a.next;
        }
        return product;
    }
}
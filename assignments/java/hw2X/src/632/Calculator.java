import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList history;
    EquationList empty;

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
        if (y == 0) {
            return x;
        }
        else {
            return add(x ^ y, (x & y) << 1);
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
        int n1 = x;
        int n2 = y;
        if (n1 < 0) {
            n1 = (add(~n1, 1));
        }
        if (n2 < 0) {
            n2 = (add(~n2, 1));
        }
        int result = 0;
        while (n2 != 0) {
            if ((n2 & 1) != 0) {
                result = add(result, n1);
            }
            n1 = n1 << 1;
            n2 = n2 >> 1;
        }
        if (x < 0) {
            result = (add(~result, 1));
        }
        if (y < 0) {
            result = (add(~result, 1));
        }
        return result;
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
        if (history == null) {
            return;
        }        
        EquationList pointer = history;
        while (pointer != null) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
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
        int reference = 0;
        EquationList pointer = history;
        if (pointer == null) {
            return;
        }
        while (reference != n) {
            System.out.println(pointer.equation + " = " + pointer.result);
            reference = reference + 1;
            pointer = pointer.next;
            if (pointer == null) {
                reference = n;
            }
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
        history = empty;        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (history == null) {
            return 0;
        }
        int sum = 0;
        EquationList pointer = history;
        while (pointer != null) {
            sum = sum + pointer.result;
            pointer = pointer.next;
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
        if (history == null) {
            return 1;
        }   
        int product = 1;
        EquationList pointer = history;
        while (pointer != null) {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}
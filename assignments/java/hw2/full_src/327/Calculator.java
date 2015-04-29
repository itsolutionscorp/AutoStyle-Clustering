import list.EquationList;

public class Calculator {
    public EquationList beginning;
    public EquationList current;
    public int length = 0;
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
        if (x == 0) {
            return y;
        }
        else if (y == 0) {
            return x;
        }
        int new_x = x ^ y;
        int new_y = x & y;
        new_y = new_y << 1; 
        return add(new_x, new_y); 
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
        if (y == 0 || x == 0) {
            return 0; 
        }
        else if (y == 1) {
            return x;
        }
        else if (x == 1) {
            return y;
        }
        else if (y == add(~1, 1)) {
            return add(~x, 1); 
        }
        if ((y & 1) == 1) {
            return add(x, multiply(x << 1, y >> 1));
        }
        return multiply(x << 1, y >> 1);

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
        if (length == 0) {
            EquationList next = new EquationList(equation, result, null);
            beginning = next;
            current = next;
            length += 1;

        }

        else {
            EquationList next = new EquationList(equation, result, null);
            current.next = next; 
            current = next;
            length += 1;
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
        EquationList current_element = beginning;
        int i = 0;
        if (length == 0 || n == 0) {
            return;
        }
        if (n > length) {
            printHistory(length);
        }
        while (i < n - 1d) {
            current_element = current_element.next;
            i += 1;
        }
        System.out.println(current_element.equation + " = " + current_element.result);
        printHistory(n - 1);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (length == 0) {
            return;
        }
        EquationList current_element = beginning;
        length = length - 1;
        int n = length; 
        while (n > 1) {
            current_element = current_element.next;
            n = n - 1;
        }
        current = current_element; 
        current.next = null; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        beginning = null;
        current = beginning;
        length = 0; 
        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int n = 0;
        EquationList current_element = beginning;
        while (current_element != null) {
            n = add(n, current_element.result);
            current_element = current_element.next; 
        }
        return n;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int n = 1;
        EquationList current_element = beginning;
        while (current_element != null) {
            n = multiply(n , current_element.result);
            current_element = current_element.next;
        }
        return n;
    }
}
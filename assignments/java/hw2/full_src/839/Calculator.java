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
        int mask = x & y;
        int flip = x ^ y;
        while (mask != 0) {
            int shift = mask << 1;
            mask = flip & shift;
            flip = flip ^ shift;
        }
        return flip;
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
        int product = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                product = add(product, x);
            }
            x = x << 1;
            y = y >>> 1;
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

    public EquationList l;
    public int len = 0;

    public void saveEquation(String equation, int result) {
        // if (l == null) {
        //     l = new EquationList(equation, result, null);
        //     len = 1;
        //     return;
        // }
        l = new EquationList(equation, result, l);
        len = len + 1;
        // EquationList pointer = l;
        // while (pointer.next != null){
        //    pointer = pointer.next;
        // }
        // pointer.next = new EquationList(equation, result, null);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(len);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i = 0;
        EquationList pointer = l;
        while (i < n) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            i = i + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        l = l.next;
        len = len - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        l = null;
        len = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int i = 0;
        int sum = 0;    
        EquationList pointer = l;
        while (i < len) {
            sum = add(sum, pointer.result);
            pointer = pointer.next;
            i = i + 1;
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
        int i = 0;
        int product = 1;    
        EquationList pointer = l;
        while (i < len) {
            product = multiply(product, pointer.result);
            pointer = pointer.next;
            i = i + 1;
        }
        return product;
    }
}
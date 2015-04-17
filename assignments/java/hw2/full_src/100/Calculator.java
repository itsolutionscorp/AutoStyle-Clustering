import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    private EquationList eList = null;

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
        do {
            int s = x ^ y;
            int c = x & y;
            x = s;
            y = c << 1;
        } while (y != 0);
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
        int product = 0;
        while (y != 0){
            if ((y & 01) != 0){
                product = add(product, x);
            }
            x <<= 1;
            y >>= 1;
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
        if(eList == null){
            eList = new EquationList(equation, result, null);
        }
        else {
            EquationList temp = eList;
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = new EquationList(equation, result, null);
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
        EquationList temp = eList;
        printAllHistHelper(temp);
    }

    private void printAllHistHelper(EquationList temp) {
        if (temp != null) {
            printAllHistHelper(temp.next); 
            System.out.println(temp.equation + " = " + Integer.toString(temp.result));
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
        EquationList temp = eList;
        int length = getLength(temp);
        while (length - n > 0) {
            temp = temp.next;
            n += 1;
        }
        printAllHistHelper(temp);
    }    

    public int getLength(EquationList temp) {
        if (temp == null) {
            return 0;
        }
        int count = 1;
        while (temp.next != null) {
            temp = temp.next;
            count += 1;
        }
        return count;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList temp = eList;
        if (eList == null) {
            return;
        }
        if (eList.next == null) {
            eList = null;
        }
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eList = null;
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
        EquationList temp = eList;
        while (temp.next != null){
            sum += temp.result;
            temp = temp.next;
        }
        return sum + temp.result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList temp = eList;
        int product = 1;
        while (temp.next != null){
            product *= temp.result;
            temp = temp.next;
        }
        return product * temp.result;
    }
}
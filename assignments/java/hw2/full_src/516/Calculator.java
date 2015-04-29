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
        int jw = x & y;
        int sum = x ^ y;
        while (jw != 0) {
            int jww = jw << 1;
            jw = jww & sum;
            sum = sum ^ jww;
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
        int prod = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                prod = add(prod, x);
            }
            y = y >>> 1;
            x = x << 1;
        }
        return prod;
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
    EquationList equa = new EquationList(null, 0, null); 
    EquationList equacopy = equa;
    int length = 0;
    public void saveEquation(String equation, int result) {
        length = length + 1;
        equa.equation = equation;
        equa.result = result;
        equa.next = new EquationList(null, 0, null);
        equa = equa.next;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int i = 1;
        while (i <= length) {
            printHistory(i);
            i = i + 1;
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
        int i = 1;
        EquationList equaa = equacopy;
        while (i < (length + 1 - n)) {
            equaa = equaa.next;
            i = i + 1;
        }
        System.out.println(equaa.equation + ' ' + '=' + ' ' + equaa.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList equaa = equacopy;
        int i = 1;
        while (i < length - 1) {
            equaa = equaa.next;
            i = i + 1;
        }
        equaa.next = new EquationList(null, 0, null);
        length = length - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       equacopy = new EquationList(null, 0, null);
       length = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList equaa = equacopy;
        int i = 1;
        while (i <= length) {
            sum = add(sum, equaa.result);
            equaa = equaa.next;
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
        int prod = 1;
        EquationList equaa = equacopy;
        int i = 1;
        while (i <= length) {
            prod = multiply(prod, equaa.result);
            equaa = equaa.next;
            i = i + 1;
        }
        return prod;
    }
}
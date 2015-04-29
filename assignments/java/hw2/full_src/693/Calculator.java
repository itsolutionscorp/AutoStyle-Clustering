import list.EquationList;

public class Calculator {
<<<<<<< HEAD
    // YOU MAY WISH TO ADD SOME
    private EquationList savedEqns = new EquationList("null", 0, null);
=======
    // YOU MAY WISH TO ADD SOME FIELDS
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd

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
<<<<<<< HEAD
        while (true) {
            int and = x & y; 
            int carry = and << 1;
            int xor = x ^ y;
            if (carry != 0) {
                x = carry;
                y = xor;
            } else {
                return xor; 
            }
        }
=======
        return -1;
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
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
<<<<<<< HEAD
        // Boolean of whether one of the numbers is negative.
        boolean temp = false; 

        // Check if x is negative 
        if (((x >>> 31) & 1) == 1) {
            temp = !temp; 
            x = new Calculator().add(~x, 1);
        }
        // Check if y is negative 
        if (((y >>> 31) & 1) == 1) {
            temp = !temp; 
            y = new Calculator().add(~y, 1);
        }

        int i = y; 
        int mult = 0; 
        while (i > 0) { 
            mult = new Calculator().add(mult, x); 
            i = new Calculator().add(i, -1);
        }

        if (temp){
            mult = new Calculator().add(~mult, 1); 
        }
        return mult;
=======
        // YOUR CODE HERE
        return -1;
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
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
<<<<<<< HEAD
        savedEqns = new EquationList(equation, result, savedEqns); 
=======
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
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
<<<<<<< HEAD
        EquationList historyList = new EquationList(savedEqns.equation, savedEqns.result, savedEqns.next);
        while (historyList.next != null) {
            System.out.println(historyList.equation + " = " + historyList.result); 
            historyList = historyList.next; 
        }
=======
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
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
<<<<<<< HEAD
        EquationList historyList = new EquationList(savedEqns.equation, savedEqns.result, savedEqns.next);
        while (historyList.next != null){
            if (n > 0) {
                System.out.println(historyList.equation + " = " + historyList.result); 
                n = n - 1; 
            }
            historyList = historyList.next; 
        }
=======
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
<<<<<<< HEAD
        savedEqns = new EquationList(savedEqns.next.equation, savedEqns.next.result, savedEqns.next.next); 
=======
        // YOUR CODE HERE
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
<<<<<<< HEAD
        savedEqns = new EquationList("null", 0, null); 
=======
        // YOUR CODE HERE
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
<<<<<<< HEAD
        int allSum = 0; 
        EquationList historyList = new EquationList(savedEqns.equation, savedEqns.result, savedEqns.next);
        while (historyList.next != null) {
            allSum = new Calculator().add(allSum, historyList.result); 
            historyList = historyList.next; 
        }
        return allSum; 
=======
        return -1;
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
<<<<<<< HEAD
        int allProduct = 1; 
        EquationList historyList = new EquationList(savedEqns.equation, savedEqns.result, savedEqns.next);
        while (historyList.next != null) {
            allProduct = new Calculator().multiply(allProduct, historyList.result); 
            historyList = historyList.next; 
        }
        return allProduct; 
=======
        return -1;
>>>>>>> 3138a0114cd767a7cc0615997ad1ab40edb3e8bd
    }
}
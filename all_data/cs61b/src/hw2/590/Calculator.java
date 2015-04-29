import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList head;
    int count = 0;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int a = (x ^ y);
        int b = ((x & y) << 1);
        if ((a & b) == 0) {
            return (a | b);
        }
        return add(a, b);
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
        if ((x == 0) || (y == 0)) {
            return 0;
        }
        if (x == 1) {
            return y;
        }
        if (y == 1) {
            return x;
        }
        int a = (y & 1);
        if (a == 1) {
            return (x + (multiply(x, y >>> 1) << 1));
        }
        return ((multiply(x, y >>> 1)) << 1);
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

        if (head == null) {
            head = new EquationList(equation, result, null);
        }

        else {
            head = new EquationList(equation, result, head);
        }
        count++;
    }

            /*EquationList temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new EquationList(equation, result, null);
        count++;*/

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
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
        if (head == null) {
            return;
        }
        if (n == 0) {
            return;
        }
        if (n < 0) {
            System.out.println("User. Wat r u doing. User. Stahp.");
            return;
        }
        EquationList temp = head;
        for (int i = 0; i < n; i ++) {
            System.out.println(temp.equation + " = " + temp.result);
            if (temp.next == null) {
                return;
            }
            temp = temp.next;
        }
        /*for (int i = 0; i < n; i ++) {
            temp = temp.next;
        }*/
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        head = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (head == null) {
            return 0;
        }
        int runningTotal = 0;
        EquationList temp = head;
        while (temp != null) {
            runningTotal += temp.result;
            temp = temp.next;
        }
        return runningTotal;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (head == null) {
            return 1;
        }
        int runningTotal = 1;
        EquationList temp = head;
        while (temp != null) {
            runningTotal *= temp.result;
            temp = temp.next;
        }
        return runningTotal;
    }
}
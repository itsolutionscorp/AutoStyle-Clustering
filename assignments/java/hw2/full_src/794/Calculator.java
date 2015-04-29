import list.EquationList;

public class Calculator {
    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    
    public int add(int x, int y) {
        int whatschanging = x & y;
        int almostit = x ^ y;
        while (whatschanging != 0) {
            whatschanging = whatschanging << 1;
            x = whatschanging;
            y = almostit;
            whatschanging = x & y;
            almostit = x ^ y;
        }
        return almostit;
        

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
        int total = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                total = add(total, x);
            }
            y = y >>> 1;
            x = x << 1;
        }
        return total;
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
        if (history == null) {
                history = new EquationList(equation, result, null);

        }
        else {
            EquationList newOne = new EquationList(equation, result, history);
            history = newOne;
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
        if (history == null) {

        }
        else {
            EquationList curr = history;
            int A = 1;
            while (curr.next != null) {
                curr = curr.next;
                A += 1;
            }
        printHistory(A);
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
        EquationList curr = history;
        for (int i=1; i <= n; i += 1) {
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history.next == null) {
            clearHistory();
        }
        else {
            history = history.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (history == null) {
            return 0;
        }
        else {
            EquationList curr = history;
            int sum = 0;
            while (curr != null) {
                sum += curr.result;
                curr = curr.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (history == null) {
            return 0;
        }
        else {
            EquationList curr = history;
            int total = 1;
            while (curr != null) {
                total *= curr.result;
                curr = curr.next;
            }
            return total;
        }
    }
}
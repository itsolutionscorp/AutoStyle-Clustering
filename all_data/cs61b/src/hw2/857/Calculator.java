import list.EquationList;

public class Calculator {

    EquationList calcHistory; 
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
        int n = x ^ y;
        int z = (x & y) << 1;
        if (z != 0) {
            return add(n, z);
        }
        return n;
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
        int n = 0;
        while (y != 0) {
            if ((y & 1) !=0) {
                n = add(n, x);
            }
            x <<= 1;
            y >>>= 1;
        }
        return n;
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
        if (calcHistory == null) {
            calcHistory = new EquationList(equation, result, null);
        }
        else {
            EquationList historyHolder = calcHistory;
            calcHistory = new EquationList(equation, result, historyHolder);
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
        EquationList container2 = calcHistory;
        int counter = 0;
        while (container2 != null) {
            printHistory(counter);
            container2 = container2.next;
            counter = counter + 1;
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
        EquationList container = calcHistory;
        int counter = 0;
        while (counter < n) {
            System.out.println(container.equation + " = " + container.result);
            container = container.next;
            counter = counter + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        calcHistory = calcHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (calcHistory != null) {
            undoEquation();
            calcHistory = calcHistory.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList sumList = calcHistory;
        int result = 0;
        if (sumList == null) {
            return result;
        }
        else {
            while (sumList != null) {
                result = add(result, sumList.result);
                sumList = sumList.next;
            }
        return result;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList mulList = calcHistory;
        int result = 1;
        if (mulList == null) {
            return 0;
        }
        else {
            while (mulList != null) {
                result = multiply(result, mulList.result);
                mulList = mulList.next;
            }
        return result;
        }
    }
}
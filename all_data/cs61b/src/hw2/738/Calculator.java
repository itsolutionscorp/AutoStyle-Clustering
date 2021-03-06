import list.EquationList;

public class Calculator {
    EquationList history;
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
        int value;
        while (mask != 0) {
            mask = mask << 1;
            value = flip ^ mask;
            mask = mask & flip;
            flip = value;
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
        int multiplied = 0;
        while (y != 0) {
            if ((y & 01) != 0) {
                multiplied = add(multiplied, x);
            }
            y = y >>> 1;
            x = x << 1;
        }
        return multiplied;
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
            history = new EquationList(equation, result, history);
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
        EquationList selectHistory = history;
        while (selectHistory != null) {
            System.out.print(selectHistory.equation);
            System.out.print(" = ");
            System.out.println(selectHistory.result);
            selectHistory = selectHistory.next;
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
        EquationList selectHistory = history;
        int i = 1;
        while (i < n) {
            selectHistory = selectHistory.next;
            i += 1;
        }
        System.out.print(selectHistory.equation);
        System.out.print(" = ");
        System.out.println(selectHistory.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = new EquationList(history.next.equation, history.next.result, history.next.next);
        
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

        
    public int helper(EquationList param) {
        int cumulativeS = 0;
        if (param == null) {
            return 0;
        }
        else {
            cumulativeS = add(cumulativeS, param.result); 
        }
        return add(cumulativeS, helper(param.next));
    }
        public int cumulativeSum() {
            return helper(history);
        }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int helper2(EquationList param) {
        int cumulativeP = 1;
        if (param == null) {
            return 1;
        }
        else {
            cumulativeP = multiply(cumulativeP, param.result); 
        }
        return multiply(cumulativeP, helper2(param.next));
    }

    public int cumulativeProduct() {
        return helper2(history);
    }

}
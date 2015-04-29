import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList currenteql;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = x & y;
        while (y != 0) {
            carry = x & y;
            x = x ^ y;
            y = carry;
            y = y << 1;
        }      
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
        int result = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                result = add(result, x);
                x = x << 1;
                y = y >>> 1;
            } else {
                x = x << 1;
                y = y >>> 1;
            }
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
        if (currenteql == null) {
            currenteql = new EquationList(equation, result, null);
        } else {
            currenteql = new EquationList(equation, result, currenteql);
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
        EquationList temp = currenteql;
        while (temp != null) {
            System.out.println (temp.equation + " = " + temp.result);
            temp = temp.next;
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
        if (currenteql != null) {
            EquationList temp = currenteql;
            int i = 1;
            while (i <= n) {
            System.out.println (temp.equation + " = " + temp.result);
            temp = temp.next;
            i = i + 1;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (currenteql != null) {
            currenteql = currenteql.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        currenteql = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (currenteql == null) {
            return 0;
        } else {
            EquationList temp = currenteql;
            int sum = 0;
            while (temp != null) {
                sum = sum + temp.result;
                temp = temp.next;
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
        if (currenteql == null) {
            return 1;
        } else {
            EquationList temp = currenteql;
            int prod = 1;
            while (temp != null) {
                prod = prod * temp.result;
                temp = temp.next;
            }
            return prod;
        }
    }
}
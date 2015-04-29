import list.EquationList;

public class Calculator {
    public EquationList lst;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = 0;
        int total = 0;
        for(int i=0;i<32;i++) {
            int a = x ^ y;
            int b = a & 1;
            int c = x & y;
            if (carry != 0) {
                if (b == 1) {
                    b = 0;
                    carry = 1;
                } else if ((b == 0) && ((c & 1) == 1)) {
                    b = 1;
                    carry = 1;
                } else {
                    b = 1;
                    carry = 0;
                }
            } else {
                carry = x & y;
                carry = carry & 1;
            }
            total = total | b << i;
            x = x >> 1;
            y = y >> 1;
        }
        return total;
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
            if ((y & 1) != 0) {
                total = add(total, x);
            }
            x = x << 1;
            y = y >>> 1;
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
        lst = new EquationList(equation, result, lst);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = lst;
        while (temp != null) {
            System.out.println(temp.equation + " = " + temp.result);
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
        EquationList temp = lst;
        for (int i=0;i<n;i++) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        lst = lst.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        lst = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        EquationList temp = lst;
        while (temp != null) {
            total += temp.result;
            temp = temp.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total = 1;
        EquationList temp = lst;
        while (temp != null) {
            total = total * temp.result;
            temp = temp.next;
        }
        return total;
    }
}
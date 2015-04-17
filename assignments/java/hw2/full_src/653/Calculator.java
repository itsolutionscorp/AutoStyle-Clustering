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
    EquationList history = new EquationList();

    public static int add(int x, int y) {
        while (y != 0) {
            int a = (x & y);
            x = x ^ y;
            y = a << 1;
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

    public static int multiply(int x, int y) {
        int a = x;
        int b = y;
        int result = 0;
        while(b != 0) {
            if ((b & 1) != 0) {
                result = add(result,a);
            }
            a <<= 1; 
            b >>>= 1;
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
        if (this.history.equation == null) {
            this.history.equation = equation;
            this.history.result = result;
        }
        else {
            EquationList dummy = new EquationList(equation, result, history);
            this.history = dummy;           
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
        EquationList dummy = history;
        while (dummy.next != null) {
            System.out.println(dummy.equation + " = " + dummy.result);
            dummy = dummy.next;
        }
        if (history.equation == null) {
            return;
        }
        System.out.println(dummy.equation + " = " + dummy.result);
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
        EquationList y = this.history;
        if (history.equation == null) {
            return;
        }
        while (i <= n) {
            System.out.println(y.equation + " = " + y.result);
            y = y.next;
            i += 1;
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
            EquationList undo = history.next;
            history = undo;           
        }

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList clear = new EquationList();
        history = clear;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList dummy = history;     
        while (dummy.next != null) {
            sum += dummy.result;
            dummy = dummy.next;
        }
        if (dummy.next == null) {
            sum += dummy.result;
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
        EquationList dummy = history;     
        while (dummy.next != null) {
            prod *= dummy.result;
            dummy = dummy.next;
        }
        if (dummy.next == null) {
            prod *= dummy.result;
        } 
        return prod;
    }
}

import list.EquationList;

public class Calculator {
    EquationList history = null;
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
        // int a = x, b = y;
        // int xor, and, temp;
        // and = a & b;
        // xor = a ^ b;

        // while (and != 0) {
        //     and <<= 1;
        //     temp = xor ^ and;
        //     and &= xor;
        //     xor = temp;
        // }
        // return (int) xor;
        // System.out.println(xor);
        int A = x;
        int B = y;
        int C = A ^ B;
        int D = A & B;
        // return 2 * D + C;
        int t;
        while (D != 0) {
            D <<= 1;
            t = C ^ D;
            D &= C;
            C = t;
        }
        return C;
}
    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    private int helper(int x, int y, int z) {
        if (z == 0) {
            return x;
        }
        else {
            if (z > 0) {
            x = add(x, y);
            return helper(x, y, add(z, -1));                
            }
            else {
            x = add(x, -y);
            return helper(x, y, add(z, 1));
            }
        }
    }
    public int multiply(int x, int y) {
        return helper(0, x, y);
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
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (history != null) {
        System.out.println(history.equation + " = " + history.result);
        history = history.next;
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
        int count = 0;
        while (count < n) {
        System.out.println(history.equation + " = " + history.result);
        history = history.next;
        count += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
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
        int ret = 0;
        while (history != null) {
        ret = add(ret, history.result);
        history = history.next;        
        }
        return ret;
    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int ret = 0;
        while (history != null) {
        ret = multiply(ret, history.result);
        history = history.next;        
        }
        return ret;
    }
}    
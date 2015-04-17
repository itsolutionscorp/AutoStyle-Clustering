import list.EquationList;

public class Calculator {
    private int sum,
                and,
                xor,
                i;
    private boolean carry;
    public EquationList hst,
                        ptr;


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
        and = x & y;
        xor = x ^ y;

        while (and != 0) { //checking for 1's to be carried
            and = and << 1;
            xor = add(and, xor);
        }
        return xor; //done, compile
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
        if (getBit(y, 0) == 1) {
            sum = x;
        } else {
            sum = 0;
        }

        i = 1;
        
        while (i < 32) {
            if (getBit(y, i) != 0) {
                sum = add(sum, x << i);
            }
            i = i + 1;
        }

        return sum; 
    }

    public int getBit(int x, int i) {
        return (((x >>> i) << 31) >>> (31 - i));
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

    int hst_count = 0;

    public void saveEquation(String equation, int result) {
        if (hst_count == 0) {
            hst = new EquationList(equation, result, null);
            hst_count ++; 
        } else {
            hst = new EquationList(equation, result, hst); 
            hst_count ++;
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
        printHistory(2147483647);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        ptr = hst;
        while (ptr != null && n != 0) {
            System.out.println(ptr.equation + " = " + Integer.toString(ptr.result));
            ptr = ptr.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (hst != null) {
            hst = hst.next;
        }   
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (hst != null) {
            hst = hst.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        ptr = hst;
        while (ptr != null) {
            sum = sum + ptr.result; 
            ptr = ptr.next;
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
        int sum = 1;
        ptr = hst;
        while (ptr != null) {
            sum = sum * ptr.result; 
            ptr = ptr.next;
        }
        return sum;
    }
}
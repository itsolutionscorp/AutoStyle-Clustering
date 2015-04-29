import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqlist;
    public int history_size = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int xor = x ^ y;
        int and = x & y;
        int shifted = and << 1;

        // Base Cases
        if (x == 0)
        {
            return y;
        }
        else if (y == 0) 
        {
            return x;
        }

        // General Handling
        if (and != 0)
        {
            return add(xor, shifted);
        }
        else
        {
            return xor;
        }
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

        //Base Case
        if (x == 0) {
            return 0;
        }
        if (y == 0) {
            return 0;
        }

        // Add Y by X number of times.
        int k = 0;
        int retval = 0;
        while (k < Math.abs(x)) {
            retval = add(retval,y);
            k += 1;
        }

        // Handle Negatives:
        if (x<0) {
            // flip the bit and add 1 (x * y) if X, the "counter" is actually negative.
            retval = add(~retval,1);
        }
        return retval;

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
        if (this.history_size == 0) {
            this.eqlist = new EquationList(equation,result, null);
        }
        else {
            this.eqlist = new EquationList(equation,result,this.eqlist);
        }
        this.history_size += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        this.printHistory(this.history_size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList ptr = this.eqlist;
        while (n > 0) {
            if ((ptr == null) || (history_size < 1)) {
                return;
            }
            if (ptr.equation == null){
                return;
            }
            System.out.print(ptr.equation);
            System.out.print(" = ");
            System.out.println(ptr.result);
            ptr = ptr.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (this.eqlist != null)
        {
            this.eqlist = this.eqlist.next;
            if (this.history_size > 0) {
                this.history_size -= 1;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.eqlist = null;
        this.history_size = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int retval = 0;
        EquationList ptr = this.eqlist;
        while (ptr != null) {
            retval += ptr.result;
            ptr = ptr.next;
        }
        return retval;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int retval = 1;
        EquationList ptr = this.eqlist;
        while (ptr != null) {
            retval *= ptr.result;
            ptr = ptr.next;
        }
        return retval;
    }
}
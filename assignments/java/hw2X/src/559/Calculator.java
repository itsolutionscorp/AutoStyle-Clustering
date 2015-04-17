import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public int sizeeqlist = 0;
    public EquationList eqlist;
    public EquationList liststart;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int[] carryArray = new int[32];
        carryArray[0] = (x & y) << 1;
        for (int i = 1; i < 32; i = i + 1) {
            carryArray[i] = ((carryArray[i-1] & (x ^ y)) << 1);
        }
        int sum = (x ^ y);
        for (int j = 0; j < 31; j = j + 1) {
            sum = sum ^ carryArray[j];
        }
        return sum;
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
        int product = Math.abs(x);
        boolean neg = false;

        if ((x == 0) || (y == 0)) {
            return 0;
        } else if ((x < 0) || (y < 0)) {
            neg = true;
            if((x < 0) && (y < 0)) {
                neg = false;
            }
        }
        
        x = Math.abs(x);
        y = Math.abs(y);
        for(int i = 1; i < y; i = i + 1) {
            product = add(product, x);
        }
        if(neg == false) {
            return product;
        } else {
            return add(~ product, 1);
        }
        
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
        if(sizeeqlist == 0) {
            eqlist = new EquationList(equation, result, null);
            liststart = eqlist;
            sizeeqlist = sizeeqlist + 1;
        } else {
            eqlist.next = new EquationList(equation, result, null);
            eqlist = eqlist.next;
            sizeeqlist = sizeeqlist + 1;
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
        printHistory(sizeeqlist);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        for (int i = 0; i < n; i = i + 1) {
            EquationList ptr = liststart;
            for (int j = 1; j < (sizeeqlist-i); j = j + 1) {
                ptr = ptr.next;
            }
            System.out.println(ptr.equation + " = " + ptr.result);
        } 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList curr = liststart;
        for(int i = 0; i < (sizeeqlist-1); i = i + 1) {
            curr = curr.next;
        }
        curr.next = null;
        sizeeqlist = sizeeqlist - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        sizeeqlist = 0;
        liststart = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if(sizeeqlist == 0) {
            return 0;
        } else {
            EquationList curr = liststart;
            for(int i = 0; i < sizeeqlist; i = i + 1) {
                sum = sum + curr.result;
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
        int product = 1;
        if(sizeeqlist == 0) {
            return 1;
        } else {
            EquationList curr = liststart;
            for(int i = 0; i < sizeeqlist; i = i + 1) {
                product = product * curr.result;
                curr = curr.next;
            }
            return product;
        }
    }
}
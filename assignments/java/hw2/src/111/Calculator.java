import list.EquationList;

public class Calculator {
    public EquationList history = null;  
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    /* public int add(int x, int y) {
        int carryForward = x & y;
        int tempX = x ^ y;
        while (carryForward > 0) {     // loop until all the carry forward's finish
            int carryForwardNew = carryForward << 1; // actually carry the 2^n forward to 2^(n+1)th base
            carryForward = tempX & carryForwardNew;
            tempX = tempX ^ carryForwardNew;               
        }
        return tempX;
    }   */

    public int add(int x, int y) {
        int carryForward = 11;
        while (carryForward != 0) {
            carryForward = x & y;
            x = x ^ y;
            y = carryForward << 1;
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
        int sum = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                sum = add(sum, x);
            }
            x = x << 1;
            y = y >>> 1;
        }
        return sum;
        // if y is a power of 2
/*        
        int helper = 0;
        int count = 0;
        while (helper !=  y) {
            helper = add(helper,2);
            count = count + 1;
        }
        return (x << count); */      
        // if y is 9 ( 00001001 ): x >> 8 + x >> 1
        
         
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
        EquationList copyHistory = history;
        while (copyHistory != null) { // do i need to do the sentinel thing? might be a secret test
            System.out.println(copyHistory.equation + " = " + copyHistory.result);
            copyHistory = copyHistory.next;
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
        EquationList dummyHistory = history;
        while ((dummyHistory != null) & (n > 0) ) {
            System.out.println(dummyHistory.equation + " = " + dummyHistory.result);
            n = n - 1;
            dummyHistory = dummyHistory.next;
        }
        return;
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
        this.history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        while (history != null) {
            sum = sum + history.result;
            history = history.next;
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
        int product = 1;
        while (history != null) {
            product = product * history.result;
            history = history.next;
        }
    return product;
    }
}
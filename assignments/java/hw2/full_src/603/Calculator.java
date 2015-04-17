/** Delanie Lowe
  * cs61b-bbq 
**/

import list.EquationList;

public class Calculator {
    
    EquationList lastStored;
    int lengthStored = 0;

    public static void main(String[] args){}

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public static int add(int x, int y) {
        int c = 0;
        int ans = 0;
        for (int i = 0; i < 32; i += 1) {
            int bx = getBit(x, i);
            int by = getBit(y, i);
            int bc = getBit(c, i);
            if (bc == 0){
                if ((bx ^ by) == 1){
                    ans = setBit(ans, i);
                } else if ((bx & by) == 1){
                    c = setBit(c, (i + 1));
                }
            } else if (bc == 1){
                if ((bx ^ by) == 1){
                    c = setBit(c, (i + 1));
                } else if ((bx & by) == 1){
                    c = setBit(c, (i + 1));
                    ans = setBit(ans, i);
                } else 
                {
                    ans = setBit(ans,i);
                }
            }
        }
        return ans;
    }
    
    /** returns the ith bit from the right of x */
    private static int getBit(int x, int i){
        return (x >> i) & 1;
    }

    /** returns a new number equal to x but with bit i set to 1*/
    private static int setBit(int x, int i){
        return x | (1 << i);
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
        int ans = 0;
        for (int i = 0; i < 32; i += 1) {
            int by = getBit(y, 0);
            if (by == 1){
                ans = add(ans, x);
            }
            x = x << 1;
            y = y >> 1;
        }
        return ans;
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
        EquationList current = new EquationList(equation, result, null);
        if (lastStored == null){
            lastStored = current;
        } else {
            current.next = lastStored;
            lastStored = current; 
        }
        lengthStored += 1;
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(lengthStored);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList storedEq = lastStored;
        for (int i = 0; i < n; i += 1){
            System.out.println(storedEq.equation + " = " + storedEq.result);
            storedEq = storedEq.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        lastStored = lastStored.next;
        lengthStored -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        lastStored = null; 
        lengthStored = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList storedEq = lastStored;
        int sum = 0;
        for (int i = 0; i < lengthStored; i += 1){
            sum += storedEq.result;
            storedEq = storedEq.next;
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
        EquationList storedEq = lastStored;
        int prod = 1;
        for (int i = 0; i < lengthStored; i += 1){
            prod *= storedEq.result;
            storedEq = storedEq.next;
        }
        return prod;
    }
}


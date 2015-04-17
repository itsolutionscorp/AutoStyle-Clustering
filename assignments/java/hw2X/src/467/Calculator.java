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

    public int add(int x, int y) {
        if (x == 0) {
            return y;
        }
        else if (y == 0) {
            return x;
        }
        else {
            int a = x ^ y;
            int carry = (x & y) << 1;
            int total = add(a, carry);
            return total;    
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
        if (y == 0) {
            return 0;
        }
        if (y == 1) {
            return x;
        } 
        else {
            int temp = x;
            if (y > 0) {
                    while (y > 1) {
                    temp = add(temp,x);
                    y = y - 1;
                }
            }
            else {
                while (y < -1) {
                    temp = add(temp, x);
                    y = y + 1;
                }
                temp = add(0, -temp);
            }
            return temp;
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
   
    public EquationList savedEqns = null; 

    public void saveEquation(String equation, int result) {
        savedEqns = new EquationList(equation, result, savedEqns);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList mutatedSavedEqns = savedEqns;
        while (mutatedSavedEqns != null) {
            System.out.println(mutatedSavedEqns.equation + " = " + Integer.toString(mutatedSavedEqns.result));
            mutatedSavedEqns = mutatedSavedEqns.next;  
        }
        //System.out.println(mutatedSavedEqns.equation + " = " + Integer.toString(mutatedSavedEqns.result));
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList mSavedEqns = savedEqns;
        int i = 1;
        while (i <= n){
            if (mSavedEqns != null) {
                System.out.println(mSavedEqns.equation + " = " + Integer.toString(mSavedEqns.result));
                mSavedEqns = mSavedEqns.next;
            }
            i += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (savedEqns != null) {
            savedEqns = savedEqns.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        savedEqns = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList mSavedEqns = savedEqns;
        int total = 0;
        if (savedEqns == null) {
            return total;
        }
        while (mSavedEqns != null) {
            total = add(total, mSavedEqns.result);
            mSavedEqns = mSavedEqns.next;
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
        EquationList mSavedEqns = savedEqns;
        int total = 1;
        if (savedEqns == null) {
            return total;
        }
        while (mSavedEqns != null) {
            total = multiply(total, mSavedEqns.result);
            mSavedEqns = mSavedEqns.next;
        }
        return total;
    }
}
import list.EquationList;

public class Calculator {
    private EquationList hist = null;

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
        int carry, ans, temp;

        carry = x & y; // outputs carryover values
        ans = x ^ y; // outputs answer values not including carry over

        while (carry != 0) { // loops until no more carry values
            carry = carry << 1; // shifts carryover values to correct position
            temp = ans ^ carry; // adds carryover with answer values
            carry = carry & ans; // replaces old carry over with new carry over
            ans = temp; // replaces old answer values with new answer values
        }
        return ans;
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
        if (x == 0 || y == 0){
            return 0;
        }
        
        int a = x;
        int b = y;
        int ans = 0;

        while (b != 0) {
            if ((b & 1) != 0) {
                ans = add(ans, a);
            }
            a = a << 1;
            b = b >>> 1;
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
        EquationList list;
        if (hist == null)
            list = new EquationList(equation, result, null);
        else {
            list = new EquationList(equation, result, hist);
        }

        hist = list;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = hist;

        if (temp != null) {
            while(temp.next != null) {
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
            }
            System.out.println(temp.equation + " = " + temp.result);
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
        EquationList temp = hist;

        if (temp != null){
            while(temp.next != null && n > 1) {
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
                n = n - 1 ;
            }
            System.out.println(temp.equation + " = " + temp.result);
        }
        
        // YOUR CODE HERE
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (hist.next != null) {
            hist = hist.next;
        } else {
            hist = null;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = hist;

        if (hist == null){
            sum = 0;
        } else {
            while (temp.next != null) {
                sum = sum + temp.result;
                temp = temp.next;
            }
            sum = sum + temp.result;
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
        EquationList temp = hist;

        if (hist == null){
            prod = 1;
        } else {
            while (temp.next != null) {
                prod = prod * temp.result;
                temp = temp.next;
            }
            prod = prod * temp.result;
        }

        return prod;
    }
}
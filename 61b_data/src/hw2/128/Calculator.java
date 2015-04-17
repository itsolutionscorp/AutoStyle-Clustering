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

    /* 
    & = and (mask)
    | = or (set)
    ^ = exclusive or (flip)
    ~  = flip (flip all)
    >> = arithmetic right (shifts to right a certin number replaces left overs with 1's)
    >>> = logical right (shifts to right then replaces left with 0)
    << = left (shifts to the let and replaces right empy's with 0)
    */
    public EquationList history;

    public int add(int x, int y) {
        int a = x;
        int b = y;
        while (b != 0) {
            int carry = a & b; /* where is the 1 carry */ 
            a = a ^ b; /*addition of digits ignoring carry*/
            b = carry << 1; /*move over carry*/
        }
        return a;
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
        int result = 0;
        int a = x;
        int b = y;
        while (b != 0) {
            if ((b & 1) != 0) {
                result += a;
            }
            a <<=1;
            b >>>= 1;
        }
            return result;
    }


        /* 
        int a = x;
        int b = y;
        int result = 0;
        while (b != 0) {
            if (((a < 0) && (b >= 0)) || ((a > 0) && (b <= 0))) {
            a = ~a;
            a = add(a, 1);
            }
            if ((b & 1) != 0) {
            result = add(result, a); 
            }
            a = a << 1;
            b = b >> 1;
        }
        return result;
    } /*


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
        if (history == null) {
            history = new EquationList(equation, result, null);
        } else {
            history = new EquationList(equation, result, history);
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
        EquationList histcopy = history;
        while (histcopy!= null) {
            System.out.println(histcopy.equation + " = " + histcopy.result);
            histcopy = histcopy.next;
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
        EquationList historycopy = history;
        int count = n;
        while ((historycopy!= null) && count != 0){
            System.out.println(historycopy.equation + " = " + historycopy.result);
            historycopy = historycopy.next;
            count -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
        history = history.next;
        }
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
        EquationList history2 = history;
        int sum = 0;
        while (history2 != null) {
            sum = add(history2.result, sum);
            history2 = history2.next;
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
        EquationList history2 = history;
        int product = 1;
        while (history2 != null) {
            product = multiply(history2.result, product);
            history2 = history2.next;
        }
        return product;
    }
}
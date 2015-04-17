import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equations = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        int mask = 1; 
        int sum = 0;
        int carry = 0;
        int i = 0;

        while (i < Integer.SIZE) { //JVM uses 32-bit int
            int a = x & mask; //bit selection
            int b = y & mask;
            i += 1;

            //sum uses |= to preserve the history,
            //but carry does not need to, so it uses =
            sum |= a ^ b ^ carry; //essentially, is the sum of bits odd?
            carry = ((a & b) | ((a ^ b) & carry)) << 1; //are exactly two of them 1?

            mask <<= 1; //move on to the next bit
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
        // YOUR CODE HERE
        int a = x;
        int b = y;
        int result = 0;
        while (b != 0) { // Iterate the loop till b==0
            if ((b & 1) != 0) { // Logical ANDing of the value of b with 01
                result = add(result, a); // Update the result with the new value of a.
            }
            a <<= 1;              // Left shifting the value contained in 'a' by 1.
            b >>>= 1;             // Right shifting the value contained in 'b' by 1.
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
        // YOUR CODE HERE
        equations = new EquationList(equation, result, equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList eqs = equations;
        int i = 0;
        while (eqs != null) {
            i += 1;
            eqs = eqs.next;
        }
        printHistory(i);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistoryHelper(EquationList eqs, int n) {
        /*EquationList neweqs = null;
        while (eqs != null) {
            neweqs = new EquationList(eqs.equation, eqs.result, neweqs);
            eqs = eqs.next;
        }
        while (n > 0) {
            System.out.println(neweqs.equation + " = " + neweqs.result);
            neweqs = neweqs.next;
            n -= 1;
        }*/
        while (n > 0) {
            System.out.println(eqs.equation + " = " + eqs.result);
            eqs = eqs.next;
            n -= 1;
        }
    }

    public void printHistory(int n) {
        // YOUR CODE HERE
        printHistoryHelper(equations, n);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList eqs = equations;
        while (eqs != null) {
            sum += eqs.result;
            eqs = eqs.next;
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
        // YOUR CODE HERE
        int product = 1;
        EquationList eqs = equations;
        while (eqs != null) {
            product *= eqs.result;
            eqs = eqs.next;
        }
        return product;
    }
}
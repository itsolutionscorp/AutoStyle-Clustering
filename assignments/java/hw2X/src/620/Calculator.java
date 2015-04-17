import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqn;
    public EquationList p_eqn;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int n1 = x;
        int n2 = y;
        int carry = x & y; //the AND operator returns a bit string representing the positions where addition carries over
        if (n2 == 0) {
            return n1;
        }
        return add(n1 ^ n2, carry << 1);
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
        int n1 = x; //n1 = c_n * 2^n + c_n-1 * 2^n-1 + ... + c_1 * 2 + c_0 * 1 where c_i = {0, 1} 
        int n2 = y; //n2 = c_m * 2^m + c_m-1 * 2^m-1 + ... + c_1 * 2 + c_0 * 1 where c_i ] {0, 1}
        int sum = 0; //named sum b/c represent the product of n1 and n2 as the sum of their constituent parts
        /*
        let n1 be rep as in its binary form (***...***) a bit string
        n1*n2 = (***...***)(c_m * 2^m + ... + c_0 * 1)
        = (***...***)(c_0 * 1) + (***...***)(c_1 * 2) + ... + (***...***)(c_m * 2^m)
        since << has the effect of multiplying a number by 2, can rep as
        = (***...***)(c_0 * 1) + (***...***)(c_1 * 1) << 1 + (***...***)(c_2 * 1) << 2 + ... + (***...***)(c_m * 1) << m
        so for each loop through a loop left shift n1 by one to simulate multiplying the next term by 2
        also right shift n2 to find c_i, the value of the bit in the bit string n2
        */
        while (n2 != 0) { // if n2 == 0 run through the length of n2 and nothing left to do
            if ((n2 & 1) == 0) { //find the last bit of n2; if it is 0 then do nothing because the n1 term will be multiplied by 0
            } else {//else it is 1 so add n1 to the sum
                sum = add(sum, n1); //bitwise addition has already been defined (implemented)
            }
            n1 = n1 << 1; //leftshift n1 to mult by 2; see above comments
            n2 = n2 >>> 1; //rightshift n2 to find value of next bit in bit string
        }
        return sum;
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
        if (eqn == null) {
            eqn = new EquationList(equation, result, null);
        } else {
            eqn =  new EquationList(equation, result, eqn);
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
        EquationList p_eqn = eqn;
        while (p_eqn != null) {
            System.out.println(p_eqn.equation + " +  " + Integer.toString(p_eqn.result));
            p_eqn = p_eqn.next;
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
        EquationList p_eqn = eqn;
        while (n > 0) {
            System.out.println(p_eqn.equation + " +  " + Integer.toString(p_eqn.result));
            p_eqn = p_eqn.next;
            n = n -1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (eqn == null) {
            EquationList eqn = null;
        } else {
            eqn = eqn.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (eqn != null) {
            eqn = eqn.next;
        }
        EquationList eqn = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList p_eqn = eqn;
        while (p_eqn != null) {
            sum = sum + p_eqn.result;
            p_eqn = p_eqn.next;
        }
        if (p_eqn == null) {
            sum = sum;
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
        EquationList p_eqn = eqn;
        while (p_eqn != null) {
            product = product * p_eqn.result;
            p_eqn = p_eqn.next;
        }
        if (p_eqn == null) {
            product = product;
        }
        return product;
    }
}
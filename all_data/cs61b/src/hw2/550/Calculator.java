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
        int k = 0;
        int sum = 0;
        int carry = 0;
        int next_carry = 0;
        while (k < 32) {
            carry = next_carry;
            next_carry = 0;
            int bit1 = getBit(x, k);
            int bit2 = getBit(y, k);
            if (bit1 == 1 & bit2 == 1 & carry == 1) {
                sum = setBit(sum, k);
                next_carry = 1;              
            }
            else if ((bit1 == 1 & bit2 == 1) || (carry == 1 & bit1 == 1) || (carry == 1 & bit2 == 1)) {
                next_carry = 1;
            }
            else if (bit1 == 1 || bit2 == 1 || carry == 1) {
                sum = setBit(sum, k);
            }
            k += 1;
        }
        return sum;
    }

    public int getBit(int x, int position) {
        int bit = x >> position;
        return bit & 1;
    }

    public int setBit(int x, int position) {
        int tmp = 1;
        int bit = tmp << position;
        return bit | x;
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
        if (y < 0 & x < 0) {
            x = add(~x,1);
            y = add(~y,1);
        }
        else if (y < 0) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        int product = 0;
        int k = 0;
        while (k < y) {
            product = add(product, x);
            k += 1;
        }
        return product;
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

    EquationList equations;
    public void saveEquation(String equation, int result) {
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
        while (equations != null) {
            System.out.println(equations.equation);
            System.out.println(equations.result);
            equations = equations.next;
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
        while (n > 0) {
            System.out.println(equations.equation);
            System.out.println(equations.result);
            equations = equations.next;
            n = add(n, -1);   
        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (equations != null) {
            equations = equations.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int cum_sum = 0;
        while (equations != null) {
            cum_sum = add(cum_sum, equations.result);
            equations = equations.next;
        }
        return cum_sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int cum_prod = 1;
        while (equations != null) {
            cum_prod = multiply(cum_prod, equations.result);
            equations = equations.next;
        }
        return cum_prod;
    }
}
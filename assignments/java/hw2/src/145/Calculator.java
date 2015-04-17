import list.EquationList;

public class Calculator {

    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList equationlist;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carryleft = ((x & y) << 1); // carry 1's left -> 10;
        int xor = x ^ y; // vertical "addition" of x and y [0+1 -> 1; 1+1 -> 0; 0+0 -> 0]
        if (carryleft != 0) { // recursive steps until nothing to "carryleft"
            return add(carryleft, xor); // continue to add vertically until nothing to "carryleft"
        }
        return xor; // if you never have to carry, then just return x ^ y
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
        int total = 0;
        while (y != 0) {
            if ((y & 01) != 0) total = add(total, x); // check to see if y has a zero at the right or not 
            x <<= 1; // left shift -> double x
            y >>>= 1; // logical right shift -> cut y in half
        }
        return total;
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
        equationlist = new EquationList(equation, result, equationlist);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // if (equationlist == null) {
        //     System.out.println(null);
        // }
        EquationList temp = equationlist;
        while (equationlist != null) {
            System.out.println(equationlist.equation + " = " + equationlist.result);
            equationlist = equationlist.next;
        }
        equationlist = temp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList copy = equationlist;
        while (n > 0) {
            System.out.println(equationlist.equation + " = " + equationlist.result);
            equationlist = equationlist.next;
            n = n - 1;
        } 
        equationlist = copy;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationlist = equationlist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationlist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (equationlist.next == null) {
            return sum;
        }
        while (equationlist != null) {
            sum += equationlist.result;
            equationlist = equationlist.next;
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
        if (equationlist.next == null) {
            return prod;
        }
        while (equationlist != null) {
            prod *= equationlist.result;
            equationlist = equationlist.next;
        }
        return prod;
    }
}
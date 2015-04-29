import list.EquationList;

public class Calculator {
    EquationList equations;
    EquationList eq_start;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0) {
            int temp = x ^ y;
            y = (x & y) << 1;
            x = temp;
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
    // public int multiply(int x, int y) {
    //     while (y != 0) {
    //         int total = (x & y) << 1;
    //         x = add(x, temp);
    //         y = y >> 1;
    //     }
    //     return x;

    // }
    public int multiply(int x, int y) {
        int total = 0;

        while (y != 0) {
            if ((y & 1) != 0) {
                total = add(total, x);
            }
            x = x << 1;
            y = y >>> 1;
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
        if (equations == null) {
            equations = new EquationList(equation, result, null);
            eq_start = equations;
        } else {
            equations = new EquationList(equation, result, equations);
            eq_start = equations;
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
        // int i = 1;
        // while (equations != null) {
        //     printHistory(i);
        //     i += 1;
        // }
        int i = 0;
        while (equations != null) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
            i += 1;
        }
        equations = eq_start;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int i = 0;
        while ((i < n) && (equations != null)) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
            i += 1;
        }
        equations = eq_start;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equations = eq_start;
        equations = equations.next;
        eq_start = equations;    
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       equations = eq_start;
       eq_start = null;
       equations = null;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        while(equations != null) {
            sum += equations.result;
            equations = equations.next;
        }
        equations = eq_start;
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
        while (equations != null) {
            product *= equations.result;
            equations = equations.next;
        }
        equations = eq_start;        
        return product;
    }
}
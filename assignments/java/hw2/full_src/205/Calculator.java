import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList next_equation = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (x != 0) {
            int new_x = (x & y) << 1;
            int new_y = x ^ y;
            x = new_x;
            y = new_y;
        }
        return y;
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
        int n = 0;
        int result = 0;
        while (y != 0) {
            int mask = 1;
            int new_x = 0;
            if ((y & mask) == 1) {
                new_x = x << n;
                result = add(new_x, result);
            }
            n = add(n, 1);
            y = y >>> 1;    
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
        EquationList save_my_equation = new EquationList (equation, result, next_equation);
       next_equation = save_my_equation;
   }
    /**
     * TASK 5B: CALCULATOR HIISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(Integer.MAX_VALUE);
            // System.out.print("x");
            // System.out.print(" + ");
            // System.out.print("y");
            // System.out.print(" = ");
            // System.out.print("result");
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList moving_pointer_equation = next_equation;
        while (n != 0 && moving_pointer_equation != null) {
        System.out.print (moving_pointer_equation.equation);
        System.out.print(" = ");
        System.out.print(moving_pointer_equation.result + "\n");
        moving_pointer_equation = moving_pointer_equation.next;
        n-=1;
            }
        }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (next_equation != null) {
            next_equation = next_equation.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (next_equation != null) {
            next_equation = next_equation.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int next_equation_results = 0;   
        if (next_equation == null) {
            return 0;
        }
        while (next_equation != null) {
        next_equation_results = add(next_equation_results, next_equation.result);
        next_equation = next_equation.next;
        }
        return next_equation_results;
    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int next_equation_results = 1;   
        if (next_equation == null) {
            return 0;
        }
        while (next_equation != null) {
        next_equation_results = multiply(next_equation_results, next_equation.result);
        next_equation = next_equation.next;
        }
        return next_equation_results;
     }
}
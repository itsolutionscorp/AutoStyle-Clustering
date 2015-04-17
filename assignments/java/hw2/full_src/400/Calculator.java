import list.EquationList;
public class Calculator {
    EquationList equations;
    boolean first_time = true;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) {
        int leftover = x & y;
        int current_sum = x ^ y; 
        int leftover_shifted;
        while (leftover != 0) { // if leftover is 0, then we return the curr sum
            leftover_shifted = leftover << 1;
            leftover = current_sum & leftover_shifted;
            current_sum = current_sum ^ leftover_shifted;
        }
        return current_sum;
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
        int sum = 0;
        while (y != 0) {
            int odd = x & 1; // if this is true, equate to itself
            if (odd != 0) { // even
                sum = add(sum, y);
            }
            x = x >> 1; // double
            y = y << 1; // half
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
        if (first_time) {
            equations = new EquationList(equation, result, null);
            first_time = false;
        } else {
            equations = new EquationList(equation, result, equations);
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
        //int length = 1;
        EquationList new_list = equations;
        while (new_list != null) {
            if (!new_list.equation.equals("")) {
                System.out.println("" + new_list.equation + " = " + new_list.result);
            }
            new_list = new_list.next;
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
        int length = 1;
        EquationList new_list = equations;
        while (new_list != null) {
            if (length == n) {
                System.out.println("" + new_list.equation + " = " + new_list.result);
                return;
            }
            length += 1;
            new_list = new_list.next;
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
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList iterative_list = equations;
        int sum = 0;
        while (iterative_list != null) {
            sum = add(sum, iterative_list.result);
            iterative_list = iterative_list.next;
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
        EquationList iterative_list = equations;
        int product = 1;
        while (iterative_list != null) {
            product = multiply(product, iterative_list.result);
            iterative_list = iterative_list.next;
        }
        return product;
    }
}
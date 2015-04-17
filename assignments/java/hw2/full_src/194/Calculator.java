import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equations;

    public Calculator() {
        equations = null;
    }

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
        int temp = 0;
        int answer = 0;
        int result;
        int x_digit;
        int y_digit;
        for (int i = 0; i < 32; i++) {
            x_digit = x << (31 - i);
            x_digit = x_digit >>> 31;
            y_digit = y << (31 - i);
            y_digit = y_digit >>> 31;
            if (temp == 0) {
                if ((x_digit & y_digit) == 1) {
                    result = 0;
                    temp = 1;
                }
                else if ((x_digit ^ y_digit) == 1) {
                    result = 1;
                    temp = 0;
                } 
                else {
                    result = 0;
                    temp = 0;
                }
            }
            else {
                if ((x_digit & y_digit) == 1) {
                    result = 1;
                    temp = 1;
                }
                else if ((x_digit ^ y_digit) == 1) {
                    result = 0;
                    temp = 1;
                } 
                else {
                    result = 1;
                    temp = 0;
                }
            }
            result = result << i;
            answer = answer ^ result;
        }
        return answer;
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
        int answer = 0;
        int y_checker = 0;
        for (int i = 0; i < 32; i++) {
            y_checker = y << (31 - i);
            y_checker = y_checker >>> 31;
            if (y_checker == 1) {
                answer = add(answer, x << i);
            }
        }
        return answer;
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
        EquationList eq = equations;
        while (eq != null) {
            System.out.println(eq.equation + " = " + eq.result);
            eq = eq.next;
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
        // YOUR CODE HERE
        EquationList eq = equations;
        for (int i = n; i > 0; i--) {
            System.out.println(eq.equation + " = " + eq.result);
            eq = eq.next;
        }
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
        int answer = 0;
        EquationList eq = equations;
        while (eq != null) {
            answer = add(answer, eq.result);
            eq = eq.next;
        }
        return answer;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int answer = 1;
        EquationList eq = equations;
        while (eq != null) {
            answer = multiply(answer, eq.result);
            eq = eq.next;
        }
        return answer;
    }
}
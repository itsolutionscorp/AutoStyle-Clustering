import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null;
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
            int extra = (x & y);
            x = x ^ y; 
            y = extra << 1;
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
    public int multiply(int x, int y) {
        int answer = 0;
        if (x > 0 && y > 0) {
            while (y > 0) {
                answer = add(answer, x);
                y -= 1;
            }
        }
        else {
            if (x > 0 && y < 0) {
                for (int counter = add(~y, 1); counter > 0; counter -= 1) {
                    answer = add(answer, x);
                }
                answer = add(~answer, 1);
            }

            else if (x < 0 && y > 0) {
                for (int counter = add(~x, 1); counter > 0; counter -= 1) {
                    answer = add(answer, y);
                }
                answer = add(~answer, 1);
            }

            else {
                x = add(~x, 1);
                for (int counter = add(~y, 1); counter > 0; counter -= 1) {
                    answer = add(answer, x);
                }
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
        EquationList temp = history;

        // if first entry in calculator
        if (history == null) {
            history = new EquationList(equation, result, null);
        }

        else {
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
        // YOUR CODE HERE
        EquationList temp = history;
        int count = 1;
        while (temp != null) {
            printHistory(count);
            temp = temp.next;
            count += 1;
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
        EquationList temp = history;
        if (history == null) {
            return;
        }

        while (n > 1 && temp.next != null) {
            temp = temp.next;
            n -= 1;
        }
        String equation = temp.equation;
        int result = temp.result;
        System.out.println(equation + " = " + result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        //// YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
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
        EquationList temp = history;
        while (temp != null) {
            sum += temp.result;
            temp = temp.next;
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
        EquationList temp = history;
        while (temp != null) {
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}
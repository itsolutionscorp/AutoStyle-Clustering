import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;

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
        int result = x ^ y;
        int temp = result;
        int shifter = (x & y) << 1;
        while (shifter != 0) {
            result = result ^ shifter;
            shifter = (temp & shifter) << 1;
            temp = result;
        }
        return result;
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
        int result = 0;
            while (x != 0) {

                if ((1 & x) != 0) {
                    result = add(result, y);
                }
                y = y << 1;

                x = x >>> 1;
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
        int counter = 1;
        EquationList t = history;
        if (t == null) {
            return;
        }
        while (t.next != null) {
            counter += 1;
            t = t.next;
        }
        printHistory(counter);
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
        EquationList keeper = history;
        if (keeper == null) {
            return;
            }
        for (int k = n; k > 0; k -= 1) {
            if (keeper == null) {
                return;
            }
            System.out.println(keeper.equation + " = " + String.valueOf(keeper.result));
            keeper = keeper.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
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
        int ans = 0;
        EquationList l = history;
        if (l == null) {
            return 0;
        }
        while (l != null) {
            ans = add(ans, l.result);
            l = l.next;
        }
        return ans;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int fin = 1;
        EquationList h = history;
        if (h == null) {
            return 1;
        }
        while (h != null) {
            fin = multiply(fin, h.result);
            h = h.next;
        }
        return fin;
    }
}
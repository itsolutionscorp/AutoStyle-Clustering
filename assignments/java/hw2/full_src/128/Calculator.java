import list.EquationList;

public class Calculator {
    EquationList equationList;
    int numEquations;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while ((x & y) != 0) {
            int temp = x;
            x = x ^ y;
            y = (temp & y) << 1;
        }
        return x ^ y;
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
        int temp = 0;
        while (y != 0) {
            int remainder = y & 1;
            if (remainder != 0) {
                temp = add(temp, x);
            }
            x = x << 1;
            y = y >> 1;
        }
        return temp;
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
        if (equationList == null) {
            equationList = new EquationList(equation, result, null);
        } else {
            equationList = new EquationList(equation, result, equationList);
        }
        numEquations += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(numEquations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  A maximum of n
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistory(int n) {
        EquationList temp = equationList;
        int counter = 0;
        while (temp != null && counter < n) {
            String formattedHistory = temp.equation + " = " + temp.result;
            System.out.println(formattedHistory);
            temp = temp.next;
            counter += 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationList = equationList.next;
        numEquations -= 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (numEquations != 0) {
            undoEquation();
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = equationList;
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
        EquationList temp = equationList;
        while (temp != null) {
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}

import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList equationHistory = new EquationList("END", 1, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/


    public int add(int x, int y) {
        int place = 0;
        int intCarry;
        while (place <= 32) {
            intCarry = x & y;
            x = x ^ y;
            y = intCarry << 1;
            place += 1;
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


    /* The "else" component of this multiply code is slightly "inspired" by code I found 
    on Stack Overflow. I had most of the body of the while loop, but I did not figure out the 
    value in setting the while loop to y != 0. Here is the link: http://stackoverflow.com/a/4895291 */
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int returnInt = 0;
        if (x > 0 || y > 0) {
            if (x > 0) {
                while (x != 0) {
                    returnInt = add(returnInt, y);
                    x -= 1;
            }
            }
            else {
                while (y != 0) {
                    returnInt = add(returnInt, x);
                    y -= 1;
                }
            }
            return returnInt;
        }

        else {
            while (y != 0) {
                if ((y & 1) != 0) {
                    returnInt += x;
                    x <<= 1;
                    y >>>= 1;
                }
                else {
                    x <<= 1;
                    y >>>= 1;
            }
            }
            return returnInt;
    }
    }

    public EquationList copyEList(EquationList list) {
        if (list.next == null) {
            return new EquationList(list.equation, list.result, null);
        }
        else {
            return new EquationList(list.equation, list.result, copyEList(list.next));
        }
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
        EquationList sneakyEHist = copyEList(equationHistory);
        equationHistory.next = sneakyEHist;
        equationHistory.equation = equation;
        equationHistory.result = result;        

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
        EquationList eHistCopy = equationHistory;
        while (!eHistCopy.equation.equals("END")) {
            System.out.println(eHistCopy.equation + " = " + eHistCopy.result);
            eHistCopy = eHistCopy.next;

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
        EquationList eHistCopy = equationHistory;
        while (n > 0) {
            System.out.println(eHistCopy.equation + " = " + eHistCopy.result);
            eHistCopy = eHistCopy.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (equationHistory.next.equation != "END") {
            equationHistory = equationHistory.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int returnSum = 0;
        EquationList HistCopy = equationHistory;
        while (HistCopy.equation != "END") {
            returnSum += HistCopy.result;
            HistCopy = HistCopy.next;

        }
        return returnSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int returnProduct = 1;
        EquationList HistCopy = equationHistory;
        while (HistCopy.equation != "END") {
            returnProduct = returnProduct * HistCopy.result;
            HistCopy = HistCopy.next;
        }
        return returnProduct;
    }

}

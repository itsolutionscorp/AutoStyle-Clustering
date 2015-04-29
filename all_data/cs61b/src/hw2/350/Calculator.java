import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList historyLst = null;
    int historyDepth = 0;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = x ^ y; //Naive sum of x and y (ignoring carried values)
        int carry = x & y; //1s where there are carried terms.
        int temp;
        while (notZero(carry)) { //Repeats until addition is carried out with no carried digit.
            carry = carry << 1; //Shifts the carried terms up one power of 2.
            temp = sum ^ carry;
            carry = carry & sum; //Finds next carried values.
            sum = temp; //Sum after this iteration.
        }
        return (sum);
    }

    public boolean notZero(int x) {
        if (x == 0) {
            return false;
        }
        return true;
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
        int product = 0;
        while (y != 0) {
            if ((y & 1) == 1) { //When the last digit is a 1.
                product = add(product, x); //Add a term of x to the running sum/product value.
            }
            x = x << 1; //Essentially increase x by a factor of 2.
            y = y >>> 1; //Move the multiplier over so that its factor of 2 matches x's.
        }
        return (product);
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
        historyLst = new EquationList(equation, result, historyLst);
        historyDepth += 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historyDepth);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (n <= historyDepth) {
            EquationList tempLst = historyLst;
            while (n > 0) {
                System.out.println(tempLst.equation + " = " + tempLst.result);
                tempLst = tempLst.next;
                n -= 1;
            }
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (historyLst != null) {
            historyLst = historyLst.next;
            historyDepth -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        historyLst = null;
        historyDepth = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList tempLst = historyLst;
        int i = historyDepth;
        while (i > 0) {
            sum = add(sum, tempLst.result);
            tempLst = tempLst.next;
            i -= 1;
        }
        return (sum);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int product = 1;
        EquationList tempLst = historyLst;
        int i = historyDepth;
        while (i > 0) {
            product = multiply(product, tempLst.result);
            tempLst = tempLst.next;
            i -= 1;
        }
        return (product);
    }
}

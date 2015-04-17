import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    EquationList history = null;
    int length = 0;

    public int add(int x, int y) {
        while ((x & y) != 0) {
            int mask = (x & y);
            int flip = (x ^ y);
            x = flip;
            y = mask << 1;
        }
        return x | y;
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
        if (y < 0) {
            if (x < 0) {
                return opti_multiply(-x, -y);
            } else {
                return opti_multiply(y, x);
            }
        } else {
            return opti_multiply(x, y);
        }
    }

    public int opti_multiply(int x, int y) {
        int result = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                result = add(result, x);
            }
            x = x << 1;
            y = y >> 1;
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
        history = new EquationList(equation, result, history);
        length = length + 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int num;
        EquationList pointer = history;
        if (length > n) {
            num = n;
        } else {
            num = length;
        }
        while (num > 0) {
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
            pointer = pointer.next;
            num = num - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
        length = length - 1;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        length = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = history;
        int num = length;
        int sum = 0;
        while (num > 0) {
            sum = add(sum, pointer.result);
            pointer = pointer.next;
            num = num - 1;
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
        EquationList pointer = history;
        int num = length;
        int pro = 1;
        while (num > 0) {
            pro = multiply(pro, pointer.result);
            pointer = pointer.next;
            num = num - 1;
        }
        return pro;        
    }
}
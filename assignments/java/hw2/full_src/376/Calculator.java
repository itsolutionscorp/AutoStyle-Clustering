import list.EquationList;

public class Calculator {

    public EquationList history;
    private int lenHistory;

    public static void main(String[] args) {
        Calculator test = new Calculator();
        test.add(3,2);
        test.multiply(4,3);
        test.add(4,9);
        test.printAllHistory();
//        test.undoEquation();
//        test.printAllHistory();
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
        int sum = x ^ y;
        int remain = (x & y) << 1;
        if (remain == 0){
            return sum;
        }
        return add(sum,remain);
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
        /* 010  0100  0011
           011  0010  0011
           110  1000  1001   */
        int result = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                 result = add(result,x);
            }
            x <<= 1;
            y >>>= 1;
        } return result;
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
        lenHistory = add(lenHistory,1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(lenHistory);
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
        if (n > lenHistory) n = lenHistory;
        for (int i = 0; i < n;i++) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
        lenHistory = add(lenHistory,-1);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        lenHistory = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = history;
        int result = 0;
        for(int i = 0; i < lenHistory; i++) {
            result = add(result,temp.result);
            temp = temp.next;
        } return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList temp = history;
        int result = 1;
        for(int i = 0; i < lenHistory; i++) {
            result = multiply(result,temp.result);
            temp = temp.next;
        } return result;
    }
}

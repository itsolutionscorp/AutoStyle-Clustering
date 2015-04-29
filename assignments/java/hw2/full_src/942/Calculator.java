import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList historyList;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y == 0){
            return x;
        }
        int add = x ^ y;
        int carry = (x & y) << 1;
        return add(add, carry);
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
        int total = 0;

        // test for negatives and remove for computations
        boolean flip = false;
        if ((x < 0) ^ (y < 0)){
            flip = true;
        }
        if (x < 0){
            x = add(~x, 1); 
        }
        if (y < 0){
            y = add(~y, 1); 
        }         
        
        // main mulitplication computations
        while (y != 0){
            if ((y & 1) != 0){
                total = add(total, x);
            }
            x = x << 1;
            y = y >> 1;
        }
        
        // convert back to negative if necessary
        if (flip){
            total = add(~ total, 1);
        }
        
        return total;
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
        historyList = new EquationList(equation, result, historyList); 
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        allHistoryHelper(historyList, -1);
    }
    
    public void allHistoryHelper(EquationList historyList, int n){
        if (historyList == null){
            return;
        } else if ((historyList.next == null) || (n == 1)) {
            System.out.println(historyList.equation + " = " + historyList.result);
        } else {
            System.out.println(historyList.equation + " = " + historyList.result);
            allHistoryHelper(historyList.next, n - 1);
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
        allHistoryHelper(historyList, n);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        historyList = historyList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        historyList = null;
    }

    public int cumulativeHelper(EquationList list, char op){
        if (list == null){
            if (op == '+'){
                return 0;
            } 
            return 1;  
        }
        if (op == '*'){
            return list.result * cumulativeHelper(list.next, op);
        }
        return list.result + cumulativeHelper(list.next, op);
    }   

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        return cumulativeHelper(historyList, '+');
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        return cumulativeHelper(historyList, '*');
    }
}
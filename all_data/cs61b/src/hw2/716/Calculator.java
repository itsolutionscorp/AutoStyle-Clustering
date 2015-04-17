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
    public int add(int x, int y) {
        // YOUR CODE HERE
       if (y == 0) {
        return x;
       } 
       else {
        return add(x^y, (x & y) << 1);
       }

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
        boolean isNegative = false;
        if (y < 0) {
            y = add(~y, 1);
            isNegative = true;
        }
        int total = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                total = add(total, x); 
            }
            x = x << 1;
            y = y>> 1;
        }
        if (isNegative) {
            total = add(~total, 1);
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

    EquationList historyList = null;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
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
        // YOUR CODE HERE
        //this is the special case for printHistory. No tests made
        printHistory(-1);
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
        EquationList listToPrint = historyList;
        while (n !=0 && listToPrint != null) {
            System.out.println(listToPrint.equation + " = " + listToPrint.result);
            listToPrint = listToPrint.next;
            n -= 1;
        }
        
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (historyList != null){
            historyList = historyList.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        historyList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int total = 0;
        EquationList sumList = historyList;
        if (historyList == null) {
            return 0;
        }
        else {
            while (sumList != null) {
                total += sumList.result;
                sumList = sumList.next;
            }
        }
        return total; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int total = 1;
        EquationList productList = historyList;
        if (historyList == null) {
            return 1;
        }
        else {
            while (productList != null) {
                total = total * productList.result;
                productList = productList.next;
            }
        }
        return total;
    }
}
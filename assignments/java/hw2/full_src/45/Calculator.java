import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int getBit(int x, int y){
        return 1 & x >> y;
    }
    
    public int setBit(int x, int y) {
        return x | 1 << y;
    }

    public int add(int x, int y) {
        int orXY = x | y;
        int indexTracker = 0;
        int sum = 0;
        boolean overFlow = false;
        
        while (orXY != 0 || overFlow) {

            if (indexTracker == 32 && overFlow) // checks for case that the overflow goes all the way to the end
                overFlow = false;

            int xDex = getBit(x, 0);
            int yDex = getBit(y, 0);

            if ((xDex ^ yDex) == 1 && !overFlow) {
                sum = setBit(sum, indexTracker);
            } 
            else if ((xDex | yDex) == 0 && overFlow) {
                sum = setBit(sum, indexTracker);
                overFlow = !overFlow;
            }
            else if ((xDex & yDex) == 1 && !overFlow) {
                overFlow = !overFlow;
            } 
            else if ((xDex & yDex) == 1 && overFlow) {
                sum = setBit(sum, indexTracker);
            }
            x >>>= 1;
            y >>>= 1;
            orXY = x | y;
            indexTracker++;
        }
        return sum;
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
        boolean oppositeSign = false;
        
        if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
            oppositeSign = !oppositeSign;
        }
        if (x < 0) {
            x = ~x;
            x = add(x, 1);
        }
        if (y < 0) {
            y = ~y;
            y = add(y, 1);
        }
        for (int i = 0; i < y; i++) {
            product = add(product, x);
        }
        if (oppositeSign) {
            product = ~product;
            product = add(product, 1);
        }
        return product;
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
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList tempHistory = history;
        int i = 1;
        while (tempHistory != null){
            printHistory(i);
            i++;
            tempHistory = tempHistory.next;
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
        EquationList tempHistory = history;
        while (n > 1){
            tempHistory = tempHistory.next;
            n -= 1;
        }
        System.out.println(tempHistory.equation + " = " + tempHistory.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while(history != null){
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
        // YOUR CODE HERE
        int sum = 0;
        EquationList tempHistory = history;
        while (tempHistory != null){
            sum += tempHistory.result;
            tempHistory = tempHistory.next;
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
        // YOUR CODE HERE
        int product = 1;
        EquationList tempHistory = history;
        while (tempHistory != null){
            product *= tempHistory.result;
            tempHistory = tempHistory.next;
        }
        return product;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
    public int histLength;

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
        int i = 0;
        int top = 0;    //creates the "top" row (above x)
        while (i < 31) {
            int currX = getBit(x, i);
            int currY = getBit(y, i);
            int currTop = getBit(top, i);
            if (((currX == 1) && (currY == 1)) || ((currX == 1 && currTop == 1) || (currY == 1 && currTop == 1)) || ((currX == 1) && (currTop == 1) && (currY == 1))){
                top = setBit(top, i);   //put a 1 in the top row if 1 + 1 or top row already contains a 1
            } 
            i++;
        }
        x = top ^ x;    //flip x and top rows
        int sum = x ^ y;    //flip x and y to obtain sum
        return sum;
    }

    private int getBit(int x, int i){
        if ((x & (1 << i)) != 0) {
            return 1;
        }
        return 0;
    }

    private int setBit(int x, int i){
        return x ^ (1 << i+1);
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
        int prod = 0;
        int i = 0;
        while (i < 31){
            int lastY = getBit(y, 0); 
            if (lastY == 1){    //if number is odd
                prod = add(prod, x);    //continually add to product
            } 
            x = x << 1;         //multiplies x by 2 each time thru loop
            y = y >>> 1;        //moves down bits of y
            i ++;
        }
        return prod;
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
            histLength++;
        } else {
            history = new EquationList(equation, result, history);
            histLength++;
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
        printHistory(histLength);
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
        EquationList toPrint = history;
        while (n > 0 && toPrint != null) {
            System.out.println(toPrint.equation + " = " + String.valueOf(toPrint.result));
            toPrint = toPrint.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
        histLength--;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
        histLength = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList input = history;
        int sum = 0;
        while (input != null){
            sum += input.result;
            input = input.next;
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
        EquationList input = history;
        int prod = 1;
        while (input != null){
            prod *= input.result;
            input = input.next;
        }
        return prod;
    }
}
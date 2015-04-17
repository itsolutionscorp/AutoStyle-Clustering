import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList nextEq = null;
    public int eqListLength = 0;
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
        int limit = 0;
        int result = 0;
        int counter = 0;

        for(int shift = 0; shift < 32; shift++){
            if (((x ^ y) & (1 << shift)) == (1 << shift)){  // should be called xOrY
                if ((result & (1 << shift)) == (1 << shift)){
                    result = ((1 << shift) ^ result) | ((1 << shift) << 1);
                }
                else{
                    result |= (1 << shift);
                }
            }
            else if (((x & y) & (1 << shift)) == (1 << shift)){
                result |= ((1 << shift) << 1);
            }
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
        // Let's say x or y is a power of 2 to the n
        int result = 0;

        for (int i = 0; i < 32; i++){
            if ((x & (1 << i)) == (1 << i)){
                result = add(result, y << i);
            }
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
        if (nextEq == null){
            nextEq = new EquationList(equation, result, null);
        }
        else{
            nextEq = new EquationList(equation, result, nextEq);
        }

        eqListLength += 1;
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
        printHistory(eqListLength);
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
        if (nextEq != null){
            EquationList temp = nextEq;
            int i = 0;

            while (i < n && temp != null){
                System.out.println(temp.equation + " = " + temp.result);
                temp = temp.next;
                i += 1;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        nextEq = nextEq.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        nextEq = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (nextEq == null){
            return 0;
        }
        else{
            EquationList temp = nextEq;
            int sum = 0;

            while (temp != null){
                sum = add(sum, temp.result);
                temp = temp.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        if (nextEq == null){
            return 1;
        }
        else{
            EquationList temp = nextEq;
            int product = 1;

            while (temp != null){
                product = multiply(product, temp.result);
                temp = temp.next;
            }
            return product;
        }
    }
}
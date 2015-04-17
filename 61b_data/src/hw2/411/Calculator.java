import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history;
    int histLength = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = 0;
        int k = 0;
        int shift = 1;

        while(x != 0 || y != 0){
            if(((x & 1) & (y & 1)) == 1){
                if(k == 1){
                    sum = sum | shift;
                }
                k = 1;
            }else if(((x & 1) ^ (y & 1)) == 1){
                if(k == 0){
                    sum = sum | shift;
                }
            }else{
                if(k == 1){
                    sum = sum | shift;
                }
                k = 0;
            }
            shift = shift << 1;
            y = y >>> 1;
            x = x >>> 1;
        }
        if(k == 1){
            sum = sum | shift;
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
        if(x == 0 || y == 0){
            return 0;
        }

        if(x < y){              // let y be smaller number
            int temp = x;
            x = y;
            y = temp;
        }

        int sum = 0;
        while(y != 1){
            if((y & 1) == 1){   // odd
                sum = add(sum, x);
                y = add(y, -1);
            }else{              // even
                x = x << 1;
                y = y >>> 1;
            }
        }
        return add(x, sum);
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
        histLength++;
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
        EquationList eqn = history;
        int i = 0;
        while(eqn != null && i < n){
            System.out.println(eqn.equation + " = " + eqn.result);
            eqn = eqn.next;
            i++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(history != null){
            history = history.next;
            histLength--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
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
        int sum = 0;
        EquationList eqn = history;
        while(eqn != null){
            sum += eqn.result;
            eqn = eqn.next;
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
        EquationList eqn = history;
        while(eqn != null){
            product *= eqn.result;
            eqn = eqn.next;
        }
        return product;
    }
}
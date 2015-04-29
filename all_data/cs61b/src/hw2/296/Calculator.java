import list.EquationList;

public class Calculator {
    private EquationList most_recent;
    private int num_equations;
    // YOU MAY WISH TO ADD SOME FIELDS
    // 
    public Calculator(){
        num_equations = 0;
    }

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y){
        int temp;
        while ((x & y) != 0){
            temp = x ^ y;
            y = ((x & y) << 1);
            x = temp;
        }
        return x | y;
    }

    // not sure why this does not work- fix sometime in the future
    // public int add(int x, int y) {
    //     int ans = 0;
    //     int carry = 0;
    //     int x_right;
    //     int y_right;
    //     for (int i = 0; i < 32; i++){
    //         x_right = getBit(x, i);
    //         y_right = getBit(y, i);
    //         if ((x_right | y_right) == 0 && carry == 1){ // both are 0s
    //             ans = setBit(ans, i);
    //             carry = 0;
    //         }
    //         else if ((x_right & y_right) == 1){ // both are 1s
    //             if (carry == 1){
    //                 ans = setBit(ans, i);
    //             }
    //             else{
    //                 carry = 1;
    //             }
    //         }
    //         else{
    //             if (carry == 1){
    //             }
    //             else{
    //                 ans = setBit(ans, i);
    //             }
    //         }
    //     }
    //     return ans;
    // }

    // // returns the i-th bit from right of x
    // public int getBit(int x, int i){
    //     x = x >> i;
    //     return x & 1;
    // }

    // //set the i-th bit from right of x to 1
    // public int setBit(int x, int i){
    //     return x | (1 << i);
    // }
    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
     public int multiply(int x, int y){
        int total = 0;
        int shift = 0;
        while (y != 0){
            if ((y & 1) == 1){
                total = add(total, x << shift);
            }
            y = y >>> 1;
            shift += 1;
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
        if (most_recent == null){
            most_recent = new EquationList(equation, result, null);
            num_equations += 1;
        }   
        else{
            most_recent = new EquationList(equation, result, most_recent);
            num_equations += 1;
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
        // YOUR CODE HERE
        printHistory(num_equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int printed = 0;
        EquationList current = most_recent;
        while (printed < n && current != null){
            System.out.println(current.equation + " = " + current.result);
            printed += 1;
            current = current.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        most_recent = most_recent.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        most_recent = null;
        num_equations = 0;
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
        EquationList ptr = most_recent;
        while (ptr != null){
            sum += ptr.result;
            ptr = ptr.next;
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
        EquationList ptr = most_recent;
        while (ptr != null){
            product *= ptr.result;
            ptr = ptr.next;
        }
        return product;
    }
}
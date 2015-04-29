import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    // Adding fields for Task 5A: 
    public int calc_count = 0;
    public EquationList calc_EL;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        //set up variables to work with
        int r;
        while(y != 0){
            r = x & y ;
            r = r << 1;
            x = x ^ y;
            y = r;
        }
        return x;
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
        int odd_y = y & 1 ;
        int odd_x = x & 1 ;

        if(x == 0 || y == 0){
            return 0;
        } else if (x == 1){
            return y;
        } else if(y == 1){
            return x;
        } else if( odd_y > 0 ){
            int new_y = add(y,-1);
            return add(x, multiply(x, new_y));
        } else if( odd_x > 0 ){
            int new_x = add(x, -1);
            return add(y, multiply(y,new_x));
        } 
        x = x << 1;
        y = y >> 1;
        return multiply(x,y);
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
        if(calc_count==0){
            calc_EL = new EquationList(equation, result, null);
            calc_count = calc_count + 1;
        } else {
            calc_EL = new EquationList(equation, result, calc_EL);
            calc_count = calc_count + 1;
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
        printHistory(calc_count);
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
        int list_counter = 0;
        EquationList pointer_EL = calc_EL;
        while(list_counter < n && list_counter < calc_count ){
            System.out.println(pointer_EL.equation + " = " + pointer_EL.result);
            pointer_EL = pointer_EL.next;
            list_counter = list_counter + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        //Case: when no entries
        EquationList temp_calc_EL = calc_EL;
        if(calc_count == 1){
            calc_count = 0;
            temp_calc_EL = null;
        } else if(calc_count > 1) {
            calc_EL = calc_EL.next;
            calc_count = calc_count - 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        EquationList temp_calc_EL = calc_EL;
        calc_count = 0;
        temp_calc_EL = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int list_counter = 0;
        int sum = 0;
        EquationList pointer_EL = calc_EL;
        while(list_counter < calc_count ){
            sum = sum + pointer_EL.result;
            pointer_EL = pointer_EL.next;
            list_counter = list_counter + 1;
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
        int list_counter = 0;
        int product = 1;
        EquationList pointer_EL = calc_EL;
        while(list_counter < calc_count ){
            product = product * pointer_EL.result;
            pointer_EL = pointer_EL.next;
            list_counter = list_counter + 1;
        }
        return product;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList saved_history = null;
    int history_length = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    /* FLIP_RESULTS represents the flip of x and y, the non-carrried over portions
     * CARRY_OVER represents the carrying over portion done by mask and left shift
     * Repeat this process until there is nothing to carry over*/  
    public int add(int x, int y) {
        int flip_result = x ^ y;
        int carry_over = (x & y) << 1;
        
        //Base case nothing to carry over
        if (carry_over == 0) {
            return flip_result;
        }

        //repeat fliping and maksing until nothing to carry over
        return add(flip_result, carry_over);

    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    /* Decrement y until 0 and always make y positive 
    */
    public int multiply(int x, int y) {
        
        //checks for zeros
        if (x == 0 || y == 0) {
            return 0;
        }

        // if both are negative, flip all +1 both --> NEG_X and NEG_Y 
        // (change signs to make both pos)
        else if (x < 0 && y < 0) {
            int abs_x = add( ~x, 1);
            int abs_y = add( ~y, 1);
            return multiply(abs_x, abs_y);
        }

        //since we are counting down y, if y is neg and x pos,
        //flip the signs of both to make y pos and x neg.
        else if (y < 0) {
            int neg_x = add( ~x, 1);
            int pos_y = add( ~y, 1);
            return multiply(neg_x, pos_y);
        }
        
        int decrement_result = multiply(x, add(y, -1));
        return add(x, decrement_result);
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
        if (saved_history == null) {
            saved_history = new EquationList (equation, result, null);
            history_length += 1;
        }
        
        else { 
            saved_history = new EquationList (equation, result, saved_history);
            history_length += 1;
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
        printHistory(history_length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointer = saved_history;
        while (pointer != null && n > 0) {
            System.out.println(pointer.equation + " = " + pointer.result);
            n -= 1;
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saved_history = saved_history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saved_history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = saved_history;
        int sum = 0;
        while (pointer != null) {
            sum += pointer.result;
            pointer = pointer.next;
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
        EquationList pointer = saved_history;
        int sum = 1;
        while (pointer != null) {
            sum *= pointer.result;
            pointer = pointer.next;
        }
        return sum;
    }
}
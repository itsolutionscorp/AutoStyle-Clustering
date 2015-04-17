import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int init_set = x | y;
        return add_helper(x, y, init_set, 1, 0, 0, 1);
    }

    public int add_helper(int x, int y, int sum, int filter, int next_carry, int curr_carry, int count){
        int isolated_x = x & filter;
        int isolated_y = y & filter;

        if (isolated_x > 0 && isolated_y > 0){
            next_carry = 1;
            sum = sum ^ filter;
        }

        if (curr_carry == 1){
            if (isolated_x == 0 && isolated_y == 0){
                sum = sum | filter;
                next_carry = 0;
            }else if (isolated_x > 0 && isolated_y > 0){
                sum = sum | filter;
                next_carry = 1;
            }else{
                sum = sum ^ filter;
                next_carry = 1;
            }
        }

        if (count == 32){
            return (sum << 1) >> 1;
        }

        filter = filter << 1;
        curr_carry = next_carry;
        next_carry = 0;
        count = count + 1;
        return add_helper(x, y, sum, filter, next_carry, curr_carry, count);
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
        int y_fragment = y;
        int isolated_y;
        int current_row = x;

        while (y_fragment != 0){
            isolated_y = y_fragment & 1;
            if (isolated_y > 0){
                product = add(product, current_row);
            }
            current_row = current_row << 1;
            y_fragment = y_fragment >>> 1;
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
        if (history == null){
            history = new EquationList(equation, result, null);
        }
        else{
            EquationList history_copy = new EquationList(history.equation, history.result, history.next);
            history = new EquationList(equation, result, history_copy);
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
        printHistory(Integer.MAX_VALUE);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList history_pointer = history;
        for (int i = 0; i < n; i++){
            if (history_pointer == null){
                return;
            }
            System.out.println(history_pointer.equation + " = " + history_pointer.result);
            history_pointer = history_pointer.next;
        }
        return;
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
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList history_pointer = history;
        while (history_pointer != null){
            sum += history_pointer.result;
            history_pointer = history_pointer.next;
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
        EquationList history_pointer = history;
        while (history_pointer != null){
            product *= history_pointer.result;
            history_pointer = history_pointer.next;
        }
        return product;
    }
}



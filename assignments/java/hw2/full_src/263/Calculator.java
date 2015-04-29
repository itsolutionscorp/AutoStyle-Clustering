import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    
    EquationList history;
    int history_length = 0;


    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = x ^ y;
        int carried_num = x & y;
        while (carried_num != 0) {
            carried_num  = carried_num << 1;
            x = sum;
            y = carried_num;
            sum = x ^ y;
            carried_num = x & y;
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
        int sum = 0;
        int shift = 0;
        if (x < 0 || y < 0) {
            x = ~x;
            y = ~y;
            x = add(x, 1);
            y = add(y, 1);
        }
        while (y != 0) {
            if ((y & 1) == 1) {
                sum = add(sum, x << shift);
                shift = add(shift, 1);
            }
            else {
                shift = add(shift, 1);
            }
            y = y >>> 1;
        }
        return sum;      
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else {
            history = new EquationList(equation, result, history);
        }
        history_length += 1;
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
        EquationList historyCopy = history;
        while (n > 0) {
            if (historyCopy == null) {
                return;
            }
            else {
                System.out.print(historyCopy.equation);
                System.out.print(" = ");
                System.out.println(historyCopy.result);
                historyCopy = historyCopy.next;
            }
            n -= 1;
        }
            
            
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history.next == null) {
            history = null;
        }
        else {
            history = history.next;
        }
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
        EquationList historyCopyForSum = history;
        int sum = 0;
        while (historyCopyForSum != null) {
            sum += historyCopyForSum.result;
            historyCopyForSum = historyCopyForSum.next;
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
        EquationList historyCopyForProd = history;
        int prod = 1;
        while (historyCopyForProd != null) {
            prod *= historyCopyForProd.result;
            historyCopyForProd = historyCopyForProd.next;
        }
        return prod;
    }
}

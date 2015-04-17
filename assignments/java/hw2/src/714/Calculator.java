import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equationHistory;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    
    public int add(int x, int y) {
        
        int added = x ^ y; //gives sum without considering any carries
        int carried = (x & y) << 1; //moves the carried digits over one just like in normal math
        if (carried != 0) {
            return add(added, carried); //this makes sure all of the carried digits are added to the result
        }
        return added;
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
        if (y == 1) {
            return x;
        }
        if (x == 1) {
            return y;
        }
        if (x == 0 || y == 0) {
            return 0;
        }
        if (y < 0) {
            int temp = y;
            y = x;
            x = temp;
        }
        if ((y < 0) && (x < 0)) {
            x = add(1, (~x));
            y = add(1, (~y));
        }

        int total = 0;
        while (y > 0) {
            int firstbit = (y & 1);
            if (firstbit == 1) {
                total = add(total, x);
            }   
            x = (x << 1);
            y = (y >> 1);
            
            
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
        EquationList current = equationHistory;
        if (equationHistory == null) {
            equationHistory = new EquationList(equation, result, null);
        }
        else {
            EquationList temp = equationHistory;
            equationHistory = new EquationList(equation, result, temp); 
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
        EquationList current = equationHistory;
        while (current != null) {
            System.out.println(current.equation+ " = " + Integer.toString(current.result)); 
            current = current.next;
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
        EquationList current = equationHistory;
        while (n > 0) {
            System.out.println(current.equation+ " = " + Integer.toString(current.result)); 
            current = current.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationHistory = equationHistory.next;
    }
    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        EquationList current = equationHistory;
        while (current != null) {
            total += current.result;
            current = current.next;
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
        int total = 1;
        EquationList current = equationHistory;
        while (current != null) {
            total = multiply(total, current.result);
            current = current.next;
        }
            
        return total;
    }
}
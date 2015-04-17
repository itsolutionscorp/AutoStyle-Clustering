import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    
    public int add(int x, int y) {
        int carryVariable = 0;
        while (y!=0) {
            carryVariable = x&y;
            x = x^y;
            y = carryVariable << 1;
        }
        return x;
    }
    

    public int getBit(int x, int index) {
        int temp = 1 << index;
        if ((x & temp) == 0) {
            return 0;
        } else {
            return 1;
        }
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
        int total = 0;
        if ((x & y) <0) {
            x = ~x;
            x = add(x, 1);
            y = ~y;
            y = add(y, 1);
        } 
        while (y!= 0){
            if((y&1)!= 0) {
                total = add(total, x);
            }
            x = x << 1;
            y = y >> 1;
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
        EquationList currentSaved = history;
        while (currentSaved != null) {
            System.out.println(currentSaved.equation + " = " + currentSaved.result);
            currentSaved = currentSaved.next; 
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
        int index = 0;
        EquationList currentSaved = history;
        while ((index < n) & currentSaved != null) {
            System.out.println(currentSaved.equation + " = " + currentSaved.result);
            currentSaved = currentSaved.next;
            index = index + 1;
        }
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
        EquationList current = history;
        int total = 0;
        while (current != null){
            total = add(total, current.result);
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
        EquationList current = history;
        int total = 1;
        while (current != null) {
            total = multiply(total, current.result);
            current = current.next;
        }
        return total;
    }
}
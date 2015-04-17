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
    public int add(int x, int y){  
        while (y != 0){
            /** Stores a new number 'c' where 'x' 
            and 'y' overlap and cause a carry
            **/
            int c = x & y;

            x = x ^ y; 

            y = c << 1;
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
    
    int result = 0;
    
        while (y != 0) {
        
            if ((y & 1) != 0) {
           
                result = add(result, x);
            }

            x = x << 1;

            y = y >>> 1;
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
        EquationList eList = new EquationList(equation, result, history);
        history = eList;

        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList hLst = history;
        while(hLst != null){
            System.out.println(hLst.equation + " " + "=" + " " + hLst.result);
            hLst = hLst.next;
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
        EquationList hLst = history;

        while(n > 0 && hLst != null){
            System.out.println(hLst.equation + " " + "=" + " " + hLst.result);
            hLst = hLst.next;
            n = n-1;
            
            

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
        EquationList hLst = history;
        int sum = 0;
        while(hLst != null){
            sum = sum + hLst.result;
            hLst = hLst.next;
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
        EquationList hLst = history;
        int product = 1;
        while(hLst != null){
            product = product * hLst.result;
            hLst = hLst.next;
        }
        return product;
    }
}
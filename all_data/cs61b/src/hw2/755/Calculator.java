import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList list = new EquationList("",0,null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int logical_or = x ^ y;
        int cond_add = (x & y) << 1;
        if(cond_add != 0){
            return add(logical_or, cond_add);
        }
        else {
            return logical_or;
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
        int count = 0;
        for(int i = 0 ; i < Math.abs(y); i++){
            count = add(count,x);
        }
        if(y >= 0){
        return count;
        }
        else {
        return -count;
        }
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
        list.next = new EquationList(list.equation, list.result, list.next);
        list.equation = equation;
        list.result = result;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList l = list;
        while(l.next != null){
            System.out.println(l.equation+" = "+l.result);
            l = l.next;
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
        int count = 0;
        EquationList l = list;
        while(l.next != null && count < n){
            System.out.println(l.equation+" = "+l.result);
            l = l.next;
            count += 1;
        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation()while(l.next != null){
            System.out.println("%s = %d",l.equation,l.result);
            l = l.next;
        } removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList l = list;
        while(l.next.next != null){
            l = l.next;
        }
        l.next = null;
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       list = new EquationList("",0,null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int count = 0;
        EquationList l = list;
        while(l.next != null){
            count += l.result;
            l = l.next;
        }
        return count;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int count = 1;
        EquationList l = list;
        while(l.next != null){
            count *= l.result;
            l = l.next;
        }
        return count;
    }
}
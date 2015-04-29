import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    EquationList lst;
    public int add(int x, int y) {
        while (y != 0) {
            int a = x & y;
            x = x ^ y;
            y = a << 1;
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
        // YOUR CODE HERE
        int i = 0;
        int r = 0; 
        while (y != 0) {
            if ((y & 1) == 1) {
                int xNew = x << i;
                r = add(r, xNew);
            }
            y = y >> 1;
            i += 1;
        }
        return r;
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
        lst= new EquationList(equation, result, lst);
        // YOUR CODE HERE
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
        EquationList t = lst;
        if(lst == null){
            return;
        }
        while(t.next != null){
            System.out.println(t.equation + " = " + t.result);
            t=t.next;
        }
        System.out.println(t.equation + " = " + t.result); 

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList t = lst;
        if(lst == null){
            return;
        }
        while(n!=1){
            System.out.println(t.equation + " = " + t.result);
            t=t.next;
            n=n-1;
        }
        System.out.println(t.equation + " = " + t.result); 
    }
        // YOUR CODE HERE   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        lst=lst.next;
        // YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        lst=null;
    }
    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList t = lst;
        int r=0;
        if(t == null){
            return 0;
        }
        while(t != null){
            r = r + t.result;
            t = t.next;
        }
        return r;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList t = lst;
        int r=1;
        if(t == null){
            return 1;
        }
        while(t != null){
            r = r * t.result;
            t = t.next;
        }
        return r;
    }
}
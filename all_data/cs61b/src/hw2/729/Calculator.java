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
    public int add(int x, int y) {
        int c=1;
        while (c!=0){
            int sol=x^y;
            c=(x&y)<<1;
            x=sol;
            y=c;
        }

        // YOUR CODE HERE
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
        int sol=0;
        if (y==0){
            return 0;
        }

        if (x==0){
            return 0;
        }

        else{
           while (y!=0){
                if ((y&1)==0){
                    sol=sol;
                }
                else{
                    sol=add(sol,x);
                } 
                x=x<<1;
                y=y>>>1;
            }
            return sol;
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
    
    EquationList a= null; 
    public void saveEquation(String equation, int result) {
        a=new EquationList(equation, result, a);
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
        EquationList b=a;
        while (b!=null) {
        System.out.println(b.equation+" = "+b.result);
        b=b.next;
        }
    }
        // YOUR CODE HERE
    

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList c=a;
        int i=0;
        while ((c!=null) && (i<n)){
        System.out.println(c.equation+" = "+c.result);
        c=c.next;
        i=i+1;
        }
        // YOUR CODE HERE
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        a=a.next;
    }

        // YOUR CODE HERE

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (a!=null){
        a=a.next;
        }
        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList b=a;
        int x=b.result;
        while (b.next !=null){
            b=b.next;
            x=add(x, b.result);
        }

        // YOUR CODE HERE
        return x;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList b=a;
        int x=b.result;
        while (b.next !=null){
            b=b.next;
            x=multiply(x, b.result);
        }

        // YOUR CODE HERE
        return x;

    }
}
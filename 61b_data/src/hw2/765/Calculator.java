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

    public EquationList hist;

    public int add(int x, int y) {
        int i= 0;
        int sum = 0;
        int carryover=0;

        while (i<32) {
            int x_ibit = (x >>> i) & 1 ;
            int y_ibit = (y >>> i) & 1 ;
            int sum_ibit = (x_ibit ^ y_ibit) ^ carryover;
            if ( (x_ibit==1 &&y_ibit==1) || (x_ibit==1 &&carryover==1) || (carryover==1 &&y_ibit==1)) {
                carryover=1;
            }
            else {carryover=0;}
            sum= sum | (sum_ibit<<i);
            i=i+1;
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
        int i= 0;
        int product= 0;

        while (i<32) {
            int y_ibit = (y >>> i) & 1;
            if (y_ibit==1) {
                product=add(product,(x<<i));                
            }
            i=i+1;
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
        hist = new EquationList(equation, result, hist);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList ptr=hist;
        while (ptr!=null) {
            System.out.println(ptr.equation + " = " + ptr.result);
            ptr=ptr.next;
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
        int i=0;
        EquationList ptr=hist;
        while (i<n && ptr!=null) {
            System.out.println(ptr.equation + " = " + ptr.result);
            i=i+1;
            ptr=ptr.next;
        }
        
    }
            


    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (hist!=null) {
            hist=hist.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hist=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (hist==null) {
            return 0;
        }
        EquationList ptr=hist;
        int s=0;

        while (ptr!=null){
            s=add(s, ptr.result);
            ptr=ptr.next;
        }

        return s;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (hist==null) {
            return 1;
        }
        EquationList ptr=hist;
        int p=1;

        while (ptr!=null){
            p=multiply(p, ptr.result);
            ptr=ptr.next;
        }

        return p;
    }
}
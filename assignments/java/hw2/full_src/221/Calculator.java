import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // (x&y) tells us which digits need to be carried
        // (x&y) << 1 performs the carry
        // x^y adds the others
        if(x==0) return y;
        if(y==0) return x;
        return add( (x&y) << 1, x^y);
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
        if(x==0 || y==0) return 0;
        if(y<0) return ~multiply(x, (~y)+1)+1;
        else{
            int ans = 0, m = 0;
            while(y>0){
                if( (y&1) == 1){
                    ans = add(ans, x<<m);
                }
                y = y>>>1;
                m = add(m,1);
            }
            return ans;    
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
        EquationList el = history;
        while(el != null){
            System.out.println(el.equation + " = " + el.result);
            el = el.next;
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
        EquationList el = history;
        while(n > 1 && el != null){
            el = el.next;
            n--;
        }
        if(el != null) System.out.println(el.equation + " = " + el.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(history != null) history = history.next;
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
        int ans = 0;
        EquationList el = history;
        while(el != null){
            ans += el.result;
            el = el.next;
        }
        return ans;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int ans = 1;
        EquationList el = history;
        while(el != null){
            ans *= el.result;
            el = el.next;
        }
        return ans;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history = new EquationList("basecaseignorethisone", 3, null);
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) {
        // YOUR CODE HERE
        int a = x^y;
        int b = (x&y) << 1;
        int c = (a | b);
        if (c == (a^b)) {
            return c;
        }
        return add(a,b);

    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int greater(int x, int y) {
        if (x>=y) {
            return x;
        }
        return y;
    }

    public int lesser(int x, int y) {
        if (x<=y) {
            return x;
        }
        return y;
    }

    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int count = greater(x,y);
        if (count <0) {
            count = add(~count,1);
        }
        int count2 = lesser(x,y);
        while (count>1) {
            count2 = add(count2,lesser(x,y));
            count-=1;
        }
        if (x<0 & y<0){
            return add(~count2,1);
        }
        return count2;
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
        // YOUR CODE HERE
        EquationList pointer = history;
        pointer.next = new EquationList(equation, result, pointer.next);
        
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
        EquationList pointer = history;
        if (pointer==null) {
            return;
        }
        if (pointer.equation == "basecaseignorethisone"){
            pointer = pointer.next; 
        }
        while(pointer != null) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
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
        // YOUR CODE HERE
        EquationList pointer = history;
        if (pointer==null) {
            return;
        }
        if (pointer.equation == "basecaseignorethisone") {
            pointer = pointer.next;
        }
        while (n>0) {
            if (pointer == null) {
                return;
            }
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            n-=1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history.equation == "basecaseignorethisone") {
            history = history.next;
        }
        if (history == null) {
            return;
        }
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        if (history.equation == "basecaseignorethisone") {
            history = history.next;
        }
        while (history != null) {
            history = history.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList pointer = history;
        if (pointer == null) {
            return 0;
        }
        if (pointer.equation == "basecaseignorethisone") {
            pointer = pointer.next;
        }
        while (pointer != null){
            sum += pointer.result;
            pointer = pointer.next;
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
        // YOUR CODE HERE
        int sum = 1;
        EquationList pointer = history;
        if (pointer == null) {
            return 1;
        }
        if (pointer.equation == "basecaseignorethisone") {
            pointer = pointer.next;
        }
        while (pointer != null){
            sum = sum * pointer.result;
            pointer = pointer.next;
        }
        return sum;
    }
}
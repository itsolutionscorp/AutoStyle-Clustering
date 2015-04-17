import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    EquationList saved = null;
    // make public variables to reupdate
    public String equation;
    public int result;
    public int counter = 0;
    public int sum = 0;
    public int prod = 1;

    
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
        int and, xor, temp;
        and =  x & y;
        xor = x ^ y;

        while (and != 0){
            and <<= 1;
            temp = xor ^ and;
            and = and & xor;
            xor = temp;
        }
        return xor;
    }

    // Citation: Stack Overflow
    // After hours of toiling, I used the help of the LAs and referenced Stack Overflow to check my work.

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
        result = 0;
        while (y != 0){
            if ((y & 01) != 0){
                result = add(result, x);    
            }
            x <<= 1;
            y >>>= 1;
        }
        return result;
    }

    // Citation: Stack Overflow
    // After hours of toiling, I used the help of the LAs and referenced Stack Overflow to check my work.

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
        // adds the newest element to the front:
        saved = new EquationList(equation, result, saved);

        // note: save each input as public and keep reupdating
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {       // call "dump"
        // YOUR CODE HERE
       
        // make a copy of saved to iterate through
        EquationList l = saved;

        if (l == null) {
            //System.out.println(); <--wrong formatting
            return;
        }
        while (l != null) {
            System.out.println(l.equation + ' ' + '=' + ' ' + l.result);
            l = l.next;   //going down the memory stack
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {    // call "history (n)"
        // YOUR CODE HERE
        int counter = 0;
        // make a copy of saved to iterate through
        EquationList l = saved;

        if (l == null) {
            //System.out.println(); <--wrong formatting
            return;
        }
        while ((l != null) && (counter < n)) {
            System.out.println(l.equation + ' ' + '=' + ' ' + l.result);
            l = l.next;
            counter += 1; 
        }
        counter = 0;   // reset counter
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE            // call "undo"
        // Goal: need to redraw pointer to the 2nd EquationList
        
        /*  ---- It is not ----
        **  EquationList l = saved;
        **  l = l.next;
        */

        saved = saved.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE            // call "clear"
        // Goal: need to redraw pointer to null/initial EquationList
        
        /*  ---- It is not ----
        ** EquationList l = saved;
        ** while (l != null) {
        **     l = l.next;
        ** }
        */

        while (saved != null) {
            saved = saved.next;
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
        sum = 0;
        EquationList l = saved;
        while (l != null) {
            sum += l.result;
            l = l.next;
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
        prod = 1;
        EquationList l = saved;
        while (l != null) {
            prod *= l.result;
            l = l.next;
        }
        return prod;    
    }
}
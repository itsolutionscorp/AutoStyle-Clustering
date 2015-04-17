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

    public EquationList history;
    public int add(int x, int y) {
        // YOUR CODE HERE

        if ((x & y) == 0){
            return (x ^ y);}


        int xor = x^y;
        int shifted_x = (x&y)<<1;

        while ((shifted_x & xor) != 0) {

            int z = shifted_x;
            shifted_x = ((shifted_x & xor)<<1);
            xor = (z ^ xor);
            

        }

        return (shifted_x ^ xor);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return t6he product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE 
       int sum = 0;
        while (y >= 1){
            
        if ((y&1)==1){
            
            sum = add(sum,x);

        }

        x = x<<1;
        y = y>>1;}

        return sum;}

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

        EquationList x = new EquationList(equation,result,history);     
        history = x;

    

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
        int x = 1;
        EquationList templist = history;
        while (templist != null){
        printHistory(x);
        templist = templist.next;
        x++;



    }}
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE

        EquationList templist = history;
        while (n > 1){
            if (templist.next == null){
                return;
            }
            templist = templist.next;
            n = n - 1;
        }
        System.out.println(templist.equation + " = " + templist.result);
         

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history == null){
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
        history = null;
        }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList templist = history;

        int sum = 0;
        while (templist != null){
            sum = sum + templist.result;
            templist = templist.next;
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
        EquationList templist = history;

        int product = 1;
        while (templist != null){
            product = product * templist.result; 
            templist = templist.next; 

    }
return product;}
}
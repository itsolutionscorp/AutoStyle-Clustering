import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    
    
    public EquationList c = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        
        if (y == 0){
            return x;
        }
        else if (x == 0){
            return y;
        }
        else{
            return add((x ^ y), (x & y) << 1);
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
        if ((y < 0) && (x > 0)){
            return multiply(y, x);
        }
        else if ((y < 0) && (x < 0)){
            return multiply(Math.abs(x), Math.abs(y));
        }

        int product = 0;
        while (y != 0){
            if ((y & 01) != 0){
                
                product = add(product, x);
                
            }
            x = x << 1;
            y = y >> 1; 
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
        c = new EquationList(equation, result, c);

        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {

        EquationList d = c;
        while (c != null){
            System.out.println(c.equation + " = " + c.result);

            c = c.next;
        }
        c = d;




        
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList x = c;
        int counter = 0;
        while (counter < n){
            System.out.println(c.equation + " = " + c.result);
            c = c.next;
            counter +=1;
        }
        c = x;
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (c!=null){
            c = c.next;
        }
       

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {

        while(c.next != null){
            c = c.next;
        }
        c = c.next;

        







    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList y = c;
        int cumSum = 0;
        while (c != null){
            cumSum = cumSum + c.result;

            c = c.next;
        }
        c = y;
        return cumSum;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList u = c;
        int cumProduct = 1;
        while (c != null){
            cumProduct = cumProduct * c.result;
            c = c.next;
        }
        c = u;
        return cumProduct;
    }
}
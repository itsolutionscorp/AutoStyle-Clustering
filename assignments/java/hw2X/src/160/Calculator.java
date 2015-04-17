import list.EquationList;

public class Calculator {

    private EquationList loc = null;
    private int size = 0;




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
        
        int t;


        while(y != 0)
        {
            t = (x & y);
            t= t<<1;
            x = (x ^ y);
            y = t;
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
        int prod=0;
        int a;
        int b;
        int i;

        if (x < 0 && y < 0) 
        {
            a = add(~x, 1);          //make pos
            b = add(~y, 1);          //make pos
        }
        else if (x > 0 && y > 0) {
            a = x;
            b = y;
        }
        else if (x < 0) // x neg, y pos
        {
            a = x;
            b = y;
        }
        else // x pos, y neg
        {
            a = y;
            b = x;
        }
        
        for (i=0; i < b ; i++)
        {
            prod = add(prod, a);
        }

        return prod;
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
      
                loc = new EquationList (equation, result, loc);
                size ++;
        

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
            printHistory(size);

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
        EquationList p = this.loc;
       
        for (int i = 0; i< (n); i++)
            {
                
                System.out.print(p.equation);
                System.out.print( " = ");
                System.out.println(p.result);
                p = p.next;
            }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        loc = loc.next;
        size --;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
         loc = null;
         size = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum= 0;
        EquationList q = this.loc;

        for (int i=0; i < size ; i++)
        {
            sum = add(sum, q.result);
            q= q.next;
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
        int prod = 1;
        EquationList q = this.loc;

        for (int i=0; i < size ; i++)
        {
            prod = multiply(prod, q.result);
            q= q.next;
        }
        return prod;
    }
}
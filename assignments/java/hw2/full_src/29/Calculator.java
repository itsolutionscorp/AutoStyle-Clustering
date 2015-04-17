import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    
    public EquationList equa;
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
        int xor=x^y;
        int carry=x&y;
        
        while(carry!=0)
            { carry= carry<<1;
              int tempCarry=carry;
              carry=tempCarry&xor;
              xor = tempCarry^xor;
          }

       return xor;
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
        int result=0;
        int tempY=y;
        for (int i=0; i<32; i++)
        {   if( (tempY & 01) ==1)
                {
                    result=add(result,x<<i);
                }
            tempY=tempY>>1;
        }
        return result;
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
        
        EquationList currentEqua = new EquationList(equation, result, null);
        if (equa==null)
            equa=currentEqua;
        else 
            {currentEqua.next=equa;
            equa=currentEqua;}
        
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
        EquationList tempEqua= equa;
        while (tempEqua!=null)
            {
                System.out.printf("%s = %d\n",tempEqua.equation,tempEqua.result);
                tempEqua=tempEqua.next;
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
        EquationList tempEqua= equa;
        for (int i=0; i<n; i++)
            {   System.out.printf("%s = %d\n",tempEqua.equation,tempEqua.result);
                tempEqua=tempEqua.next;}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equa=equa.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equa=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList tempEqua= equa;
        int result=0;
        while (tempEqua!=null)
            {
                result=result+tempEqua.result;
                tempEqua=tempEqua.next;    
                        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList tempEqua= equa;
        int result=1;
        while (tempEqua!=null)
        {
            result=result*tempEqua.result;
            tempEqua=tempEqua.next;    
        }
        return result;
}
}
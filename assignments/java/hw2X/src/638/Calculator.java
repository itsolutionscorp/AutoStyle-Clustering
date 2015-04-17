import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList historySave;
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
       /*UPDATE NEEDED*/
        while (y!=0)
        {
            
            int mask = x & y;
            x=x^y;

            y= mask <<1;
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
        
        int seed = 1;
        int result = 0;
        int twoMult=0;
        int addIn = 0;
        int negAddIn = 0;
        
        if (y<0) {
            x = add (~x, 1);
            y = add (~y, 1);
            /*negate both numbers*/
            /*don't negate something repeatedly in a loop*/
        }

        if (y==1){
            return x;
        }

        while (y>1) {
            
            twoMult = seed & y;
            seed = seed << 1;
            
            addIn = multByTwo(x, twoMult);
            result = add(result, addIn);
            negAddIn = add(~addIn, 1);
            y = add(y, negAddIn);
            


             
        }
         return result;
    }   
    public int multByTwo (int x, int y){
        if (y==0){
            return 0;
        } 
       
        
        while (y>1) {
                
                x= x << 1;
                y = y >>> 1;
                
            }
        
        return x;
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
        historySave = new EquationList (equation, result, historySave);

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
        EquationList ptr = historySave;
        while (ptr.next!=null) {
            System.out.print(ptr.equation + "= ");
            System.out.println(ptr.result);
            ptr.next=ptr;
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
        EquationList ptr = historySave;
        // YOUR CODE HERE
        while (n>0) {
            System.out.print(ptr.equation + "= ");
            System.out.println(ptr.result);
            ptr=ptr.next;
            n=n-1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        historySave=historySave.next;
        // YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList ptr = historySave;
        int result = 0;
        while (ptr.next!=null){
            result = add(result, ptr.result);
            ptr=ptr.next;

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
        EquationList ptr = historySave;
        int result = 1;
        while (ptr.next!=null){
            result = multiply(result, ptr.result);
            ptr=ptr.next;

        }
        return result;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public Calculator(){

    }

    public Calculator(EquationList history) {
        this.history = history;
    }
    public int add(int x, int y) {
        // YOUR CODE HERE
        int result = x;
        int carrier = y;
        
        int i = 0;
    while (i <= 32)
    {
        result =  x ^ y;

        carrier = x & y;
        carrier = carrier << 1;

        x = result;
        y = carrier;

        


        i++;
    }
    return result;
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
        
        int i = 0;
        int result = 0;
        int toadd = 0;

        while (i < 32) {
            int current = getBit(y, i);
            if (current == 0) {
             toadd = x & current;
             }
            
            
            else {
                int multer = ~0;
                toadd = x & multer;
                toadd = toadd << i;

            }
            
            result = add(result, toadd);


            i++;
        }
        System.out.println("");
        return result;
    }

    public int getBit(int x, int y) {
        int compare = x >>> y;
        compare = compare & 1;
        return compare;
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
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else 
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
        EquationList runner = history;
        while (runner != null) {

        System.out.println(runner.equation + " = " + runner.result);
        runner = runner.next;


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
         EquationList runner = history;
        while (n <=0) {

                    
       

        System.out.println(runner.equation + " = " + runner.result);
        runner = runner.next;
        n--;

     
         
            




        }

  
    }
        
       

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {

        
    history = history.next;
    

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
        EquationList p =history;
        int sum = 0;
        while (p != null) {
            sum += p.result;
            p = p.next;
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
        EquationList p =history;
        int prod = 1;
        while (p != null) {
           prod = prod * p.result;
            p = p.next;
        }
        return prod;
    }
}
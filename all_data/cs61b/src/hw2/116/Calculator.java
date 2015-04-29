import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equationstring = null;
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

         if (y==0) {
             return x;
         }
        return add(x^y, (x&y)<< 1  );
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
        int sum = 0;
        int a = x;
        int b = y;
        int sign = 0;
        /*
        while (a != 0){
            if ((a&1) != 0){
                if (sum == 0){
                sum = add(sum,b)
            }
                else{
                    sum = add(sum, b << 1)
                }
            }
                a = a >>> 1  
        }
        */
        if (x < 0 && y > 0){
            sign = 1;

        }
         if (y < 0 && x > 0){
            sign = 1;

        }
        if (x < 0){
            x = ~x;
            x = add(x,1);
            
        }
          if (y < 0){
            y = ~y;
            y = add(y,1);

            
        }

        
        for (int i=0;x != 0; i++){
             if ((x&1) != 0){
                
                sum = add(sum,y <<= i);
            }
            x >>>= 1;
        }

        if (sign==1){
            sum = ~sum ;
            sum = add(sum,1);
        }
        return sum;
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
        equationstring = new EquationList(equation, result, equationstring);
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
        EquationList copy = equationstring;
        while (equationstring != null){
        System.out.println(equationstring.equation + " = " + equationstring.result);
        equationstring = equationstring.next;

    }
    equationstring = copy;
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
        EquationList copy = equationstring;
        for (int i = 0; i < n; i++){
            System.out.print(equationstring.equation + " = " + equationstring.result);
            System.out.println("");
            equationstring = equationstring.next;
        }
        equationstring = copy;
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equationstring = equationstring.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationstring = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sumresults = 0;
        EquationList copy = equationstring;
        while (equationstring != null){
            sumresults += equationstring.result;
            equationstring = equationstring.next;
        }
        
        equationstring = copy;
        return sumresults;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int productresults = 1;
        EquationList copy = equationstring;
        while (equationstring != null){
            productresults *= equationstring.result;
            equationstring = equationstring.next;
        }
        equationstring = copy;
        return productresults;

    }
}
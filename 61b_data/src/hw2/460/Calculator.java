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
    public EquationList history =  null;
    //public EquationList historyPrevious;
    //public int counter = 0;


    public int add(int x, int y) {
        // YOUR CODE HERE
        

        while (y != 0) {

            int carry = x & y;

            x = x ^ y;

            y = carry << 1;


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

        boolean negativeX = false;
        boolean negativeY = false;
        boolean negativeBoth = false;

        if (x == 0 || y == 0){
            return 0;
        }if (x == 0 && y == 0){
            return 0;
        }if (x == 1){
            return y;
        }if (y == 1){
            return x;
        }

        if(x < 0 && y < 0){
            negativeBoth = true;
        }else if(x < 0){
            negativeX = true;
        }else if(y < 0){
            negativeY = true;
        }

        if(negativeX){
            // x = add((x ^ add((int) Math.pow(2, 31), -1)), 1);
            x = add(~x, 1);
        }
        if(negativeY){
            // y = add((y ^ add((int) Math.pow(2, 31), -1)), 1);
            y = add(~y, 1);
        }
        if(negativeBoth){
            // x = add((x ^ add((int) Math.pow(2, 31), -1)), 1);
            // y = add((y ^ add((int) Math.pow(2, 31), -1)), 1);
            x = add(~x, 1);
            y = add(~y, 1);
        }
        int result = 0;

        while (y != 0){

            if ((y & 1) == 1){
                result = add(result, x);
            }
            y = y >>> 1;
            x = x << 1;
        }
        if(negativeY){
           // return add((result ^ add((int) Math.pow(2, 31), -1)), 1);
            result = add(~result, 1);
            return result;
        }else if(negativeX){
            // return add((result ^ add((int) Math.pow(2, 31), -1)), 1);
            result = add(~result, 1);
            return result;
        }else{

            return result;
        
        
    }}

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
        // YOUR CODE HERE
        // int printHistoryCountner = 0;
        // while(printHistoryCountner < counter){
        // printHistory(printHistoryCountner);
        // printHistoryCountner = printHistoryCountner + 1;
        // }
        EquationList historyCopy = history;
        while (historyCopy != null){
        System.out.println(historyCopy.equation + " = " + historyCopy.result);
        historyCopy = historyCopy.next;

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
        EquationList historyCopy = history;
        for(int k = 0; k < n; k ++){
            System.out.println(historyCopy.equation + " = " + historyCopy.result);
            historyCopy = historyCopy.next;
        }
        

        ///Fix printHistory; 
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE

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
        //그냥 result들만 loop돌리면서 다 더해
        int cumulSum = 0;
        EquationList historyCopy = history;
        while(historyCopy != null){
            cumulSum = add(cumulSum, historyCopy.result);
            historyCopy = historyCopy.next;
        }
        return cumulSum;


    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        //그냥 result들만 loop돌리면서 다 곱해
        int cumulProduct = 1;
        EquationList historyCopy = history;

        while(historyCopy != null){
            cumulProduct = multiply(cumulProduct , historyCopy.result);
            historyCopy = historyCopy.next;
        }
        

            return cumulProduct;
        }
    }
    

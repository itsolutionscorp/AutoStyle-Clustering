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
    EquationList saveList = new EquationList("", 0 , null);
    public int add(int x, int y) {
        // YOUR CODE HERE
        //return (~167);
        /* some sketch
        01101   num1     01   01   011   00 01 011 
              
                num2     01   00   011   00 00 001

        00101            10   01   110   00 01 100
       
        10010   and      01   00   011   00 00 001     
       
        00101
        01010
        01010
        01000   xor      00   01   000   00 01 010  
 
                or       01   01   011   00 01
                */

        // Two cases. 1) carry one will not cause another carry next/
                    //2) carry one will cause another carry.
        // case one
        if ((((x^y) & (x&y) << 1) == 0 )  ){ 
            return  (x ^ y) ^ ((x & y) << 1);
            }
        // case two
        return add(x^y,(x & y) << 1);
        
           
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
        if( x == 0 || y == 0){ // either one is 0
            return 0;
        }
        // Y > 0
        else if(y > 0){        
            if( y % 2 == 0 ){
                return multiply(x<<1, y/2);
            }
            return add(x, multiply(x << 1, (y - 1) / 2));
        }
        // Y < 0
        else {
            return multiply(~x + 1, ~y + 1);
                    }
               
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
            if (saveList.equation.equals("")){
            saveList.equation  = equation;
            saveList.result = result;
            saveList.next = new EquationList("", 0 , null);
        }
        else{
            EquationList temp2 = new EquationList(equation, result, saveList);
            saveList = temp2;

        }

    }
    

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = saveList;
        while(temp.next != null){
            System.out.println(temp.equation + " = " + Integer.toString(temp.result));
            temp = temp.next;
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

        EquationList temp = saveList;
        while(n > 0){
            if(temp.next != null) {
            System.out.println(temp.equation + " = " + Integer.toString(temp.result));
            temp = temp.next;
        } else{
            System.out.println("End of history"); // if there are less than n equations 
            break;
            }
        n -= 1;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saveList = saveList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saveList = new EquationList("", 0 , null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = saveList;
        int total = 0;
        while(temp.next != null){

            total += add(0, temp.result);
            temp = temp.next;
        }
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
         EquationList temp = saveList;
        int total = 1;
        while(temp.next != null){

            total *= multiply(1, temp.result);
            temp = temp.next;
        }
        return total;

            }
}
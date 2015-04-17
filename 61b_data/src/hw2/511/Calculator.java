import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList L = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

    int a = x & y;
    int total = x ^ y;

    if (y == 0){
        return x;}
    if (x == 0){
        return y;}

    while (a != 0){

        //shift the carry
        y = a << 1;
        a = y & total;
        total = total ^ y;

    }   
    return total;


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
        if (x == 0 || y ==0){
            return 0;
        }
        if (x == 1) return y;
        if (y == 1) return x;
        int k = 0;
        while (y != 0){
             if ((y&1)==1){
                 k = add (k,x);
             }   
        // multiply the number by two in each iteration
        x = x << 1;
        // reduce the size to one to make it into 1-D array
        y = y >>> 1;
        }
        return k;
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

        EquationList new_list = new EquationList(equation, result, L);
        L = new_list;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {

        printHistory(Integer.MAX_VALUE);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistory(int n) {
        EquationList new_list = L;
        int i = 0;
        while (new_list!= null && i < n){
            System.out.println( new_list.equation + " = " + new_list.result);
            new_list= new_list.next;
            i = i + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
         if (L!= null){
            L = L.next;
         }
    } 

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (L!= null){
            L = L.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        EquationList lst = L;
        int total = 0;
        while ( lst != null){
            total += lst.result;
            lst = lst.next;
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
       
        EquationList lst = L;
        int total = 1;
        while ( lst != null){
            total *= lst.result;
            lst = lst.next;
        }
        return total;

}
}
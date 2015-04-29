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
    public int add(int x, int y) {
        while (x != 0){
            int temp = x & y;
            y = y ^ x;
            x = temp << 1;
        }
        return y;
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
        if (x==0 || y==0) {
            return 0;
        }
        
        if (x == 1){
            return y;
        } else if (y == 1){
            return x;
        }
        int temp = 0;
        /*int greater;
        int lesser;
        if (x>y){
            greater = x;   
            lesser = y;
        } else if (x<y){
            greater = y;
            lesser = x;
        } else {
            greater = x;
            lesser = y; 
        }*/
        /*int z = y & 1;*/
        while(y!=0){
            if ((y&1) == 1){    
                temp = add(temp, x);
                /*y >>= 1;*/
            }
            x <<= 1;
            y >>= 1;
        }
        return temp;
    }
    /*Credit to StackOverflow for showing me the methodology on how to check 
      number is even or odd at 
      http://stackoverflow.com/questions/160930/how-do-i-check-if-an-integer-is-even-or-odd
      Also one of the GSI's at office hours after lecture reminded me that 
      <<= will shift the number by some power of 2 and told me to think 
      about the multiplication as a number represented as a sum of powers of 2 */
    
    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    /*public EquationList a = b;*/
    public EquationList a;
    public void saveEquation(String equation, int result) {
        EquationList b = new EquationList(equation, result, a);
        a = b;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList b = a;
        while (b != null){
            System.out.println(b.equation + " = " + b.result);
            b = b.next;
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
        EquationList b = a;
        for (int i = 0; i < n; i++){
            System.out.println(b.equation + " = " + b.result);
            b = b.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        a = a.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (a != null){
            a = a.next;
        }  
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int temp = 0;
        EquationList b = a;
        if (b == null){
            return 0;
        }
        while (b != null){
            temp = temp + b.result;
            b = b.next;
        }
        return temp;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int temp = 1;
        EquationList b = a;
        if (b == null){
            return 1;
        }
        
        while (b != null){
            temp = temp * b.result;
            b = b.next;
        }
        return temp;
    }
}
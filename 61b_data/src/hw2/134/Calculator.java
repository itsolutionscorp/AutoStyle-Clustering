import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList saved = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
     if (x == 0){
        return y;
     }
     if (y == 0){
        return x;
     }
     if ((getBit(x, 0) & getBit(y, 0)) == 1){
        return (add(add(x>>>1, y>>>1), 1))<<1;
     }
     else {
        return ((add(x>>>1, y>>>1))<<1)|((getBit(x, 0)|getBit(y, 0)));
     }
    }
    private int getBit(int x, int i){
      return (x >> i) & 1;
    }
    private int setBit(int x, int i){
        int k = 1;
        int h = x;
       while(h != 0){
        k = k + 1;
        h = h >>> 1;
       }
       return x | (1 << (k - i));

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
        int i = 0;
        int k = 0;
        int l = y;
        while(l != 0){
            if (getBit(y, i) == 1){
                k = add(k, (x << i));
            }
            i = add(1, i);
            l = l >>> 1;
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
            saved = new EquationList(equation, result, saved);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
    EquationList h = saved;
    while(h != null){
    System.out.println(h.equation + " = " + h.result);
    h = h.next;
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
    int k = 0;
    EquationList h = saved;
    while(k != n){
    System.out.println(h.equation + " = " + h.result);
    h = h.next;
    k = k + 1;
}
    }   

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saved = saved.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saved = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList h = saved;
        int summation = 0;
        while(h != null){
            summation = h.result + summation;
            h = h.next;
        }
        return summation;
            }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList h = saved;
        int product = 1;
        while(h != null){
            product = h.result * product;
            h = h.next;
        }
        return product;
    }
}
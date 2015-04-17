import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqHistory = null;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int i = 0;
        boolean carry = false;
        int result = 0;
        
        while(i <= 31){
            if(carry && (getBit(x, i) & getBit(y,i)) == 1){
                result = setBit(result, i, 1);
            }
            else if(carry && (getBit(x, i) | getBit(y,i)) == 1){
                result = setBit(result, i, 0);
            }
            else if(carry && (getBit(x, i) | getBit(y,i)) == 0){
                result = setBit(result, i, 1);
                carry = false;
            }
            else if((carry == false) && (getBit(x, i) ^ getBit(y,i)) == 1){
                result = setBit(result, i, 1);
            }
            else if((carry == false) && (getBit(x, i) & getBit(y,i)) == 1){
                result = setBit(result, i, 0);
                carry = true;
            }
               
            i += 1;
        }
        return result;
    }

    public int getBit(int x, int i){
        return (x >> i) & 1;
    }
    
    public int setBit(int x, int i, int val){
        if(val == 0)
            x = x & ~(1 << i);
        else
           x = x | (1 << i);
        
        return x;
    }
    
    public int getPartialProduct(int x, int i){
        return x << i;
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
        /*int i = y;
        int result = x;
        while(i > 1){
            result = add(result, x);
            i = i - 1;
        }
        
        return result;*/
        
        int i = 0;
        int result = 0;
        
        while(i <= 31){
            if(getBit(y, i) == 1){
                result = add(result, getPartialProduct(x, i));
            }
            
        i = i + 1;
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
        if(eqHistory == null)
            eqHistory = new EquationList(equation, result, null);
        else
            eqHistory = new EquationList(equation, result, eqHistory);
            
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList eq = eqHistory;
        
        if(eq != null){
            
        while(eq != null){
            System.out.println(eq.equation + " = " + Integer.toString(eq.result));
            eq = eq.next;
        }
            
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
        EquationList eq = eqHistory;
        while(n != 0){
            System.out.println(eq.equation + " = " + Integer.toString(eq.result));
            eq = eq.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqHistory = eqHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int result = 0;
        
        if(eqHistory == null){
            return 0;
        }
        else{
            EquationList eq = eqHistory;
            while(eq != null){
                result = result + eq.result;
                eq = eq.next;
            }
            return result;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int result = 1;
        
        if(eqHistory == null){
            return 0;
        }
        else{
            EquationList eq = eqHistory;
            while(eq != null){
                result = result * eq.result;
                eq = eq.next;
            }
            return result;
        }
       
    }
}
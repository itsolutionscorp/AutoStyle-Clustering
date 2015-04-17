import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqList; 
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int tempAnd1 = (x & y) << 1;
        int ans = x ^ y;
 
        while(tempAnd1 != 0){
            int tempAnd2 = (tempAnd1 & ans) << 1;
            ans = tempAnd1 ^ ans;
            tempAnd1 = tempAnd2;
        }

        return ans;
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
        int ans = 0;
        int a = 0;
        int b = 0;
        if(x >= 0){
            a = x;
            b = y;
        } else if(y>=0){
            a = y;
            b = x;
        } else {
            a = add(~x,1);
            b = add(~y,1);
        }

      while(a!=0){
            ans = add(b,ans);
            a -= 1;
        }
        return ans;
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
        
        eqList = new EquationList(equation, result, eqList);
     
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList tempList = eqList;
        while(tempList != null){
            String eq = tempList.equation;
            int result = tempList.result;
            tempList = tempList.next;

            System.out.println(eq+ " = " +Integer.toString(result));
        }
    }    
    
    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printHistory( int n){
        EquationList tempList = eqList;
        while(tempList != null && n>0){
            String eq = tempList.equation;
            int result = tempList.result;
            tempList = tempList.next;
            System.out.println(eq+" = "+Integer.toString(result));
            n--;

        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqList = eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
       eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList templist = eqList;
        int ans = 0;
        while(templist!= null){
            ans += templist.result;
            templist = templist.next;
        }
        return ans;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
         EquationList templist = eqList;
        int ans = 1;
        while(templist!= null){
            ans *= templist.result;
            templist = templist.next;
        }
        return ans;
    }
}
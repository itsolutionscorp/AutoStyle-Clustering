import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqlist;

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
        int z;
        while(y!=0) {
            z=x&y;
            x=x^y;
            y=z<<1;
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
        int product=0;
        while(y!=0) {
            if((y&1)!=0) {
                product=add(product, x);
            }
            x=x<<1;
            y=y>>>1;
        }
        return product;
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
        EquationList temp = eqlist;
        eqlist=new EquationList(equation, result, temp);
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
        if(eqlist==null) {
            return;
        }
        allHistoryHelper(eqlist);
    }
    
    public void allHistoryHelper(EquationList L) {
        if(L.next==null) {
            System.out.println(L.equation+" = "+L.result);
            return;
            }
        System.out.println(L.equation+" = "+L.result);
        allHistoryHelper(L.next);
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
        if(eqlist==null) {
            return;
        }
        historyHelper(eqlist, n-1);
    }    

    public void historyHelper(EquationList L, int n) {
        if(n>0 && L.next==null) {
            System.out.println(L.equation+" = "+L.result);
            return;
        }
        if(n==0) {
            System.out.println(L.equation+" = "+L.result);
            return;
            }
        System.out.println(L.equation+" = "+L.result);
        historyHelper(L.next, n-1);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (eqlist==null) {
            return;
        }
        eqlist=eqlist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqlist=null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        return sumHelper(eqlist);
    }

    public int sumHelper(EquationList L) {
        if (L==null) {
            return 0;
        }
        return L.result+sumHelper(L.next);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        return prodHelper(eqlist);
    }

    public int prodHelper(EquationList L) {
        if (L==null) {
            return 1;
        }
        return L.result*prodHelper(L.next);
    }
}
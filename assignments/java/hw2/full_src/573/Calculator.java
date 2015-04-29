import list.EquationList;

public class Calculator {
    int a = 0;
    EquationList saved = new EquationList("first", 0, null);
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
        if (y == 0) {
            return x;
        }
        else{
            return add(x^y,(x&y)<<1);
        }
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
        if (y == 0) {
            return 0;
        }
        else if (y>0) {
            return (add (x, multiply(x, add(y, add(~1, 1)) )));
        }
        else {
            return add(~(multiply(x, add(~y,1))) , 1);
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
        if (a == 0) {
            saved.equation = equation;
            saved.result = result;
            a = a+1;}
        else {
            EquationList neew = saved;
            while (neew.next != null) {
                neew = neew.next;
                }
            neew.next = new EquationList(equation, result, null);       
            a = a+1;    
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
        printHistory(a);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList neeew = saved;
        int en = n;
        if ((a == 0) | (n ==0)) {

        }
        else if (a == 1) {
            System.out.println(neeew.equation + " = " + ("" + neeew.result));
        }
        else {
            if (n > a) {
                printHistory(n -1);
            }
            else {
                while (a > n) {
                neeew = neeew.next;
                n = n + 1;
            }
            printHistory(en - 1);
            System.out.println(neeew.equation + " = " + ("" + neeew.result));
            
        }
    }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList neeeew = saved;
        if (a == 1) {
            saved = new EquationList("original", 0, null);
            a = a -1;
        }
        else if (a == 2){
            neeeew.next = null;
            a = a-1;
        }
        else {
        while (neeeew.next.next != null) {
            neeeew = neeeew.next;
        }
        neeeew.next = null;
        a = a-1;
    }
}

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList original = new EquationList("original", 0, null);
        saved = original;
        a = 0;
        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList ssum = saved;
        if (a == 0) {
            return 0;
        }
        else {
            while (ssum != null){
                sum = sum + ssum.result;
                ssum = ssum.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int pro = 1;
        EquationList ppro = saved;
        if (a == 0) {
            return 1;
        }
        else {
            while (ppro != null){
                pro = pro * ppro.result;
                ppro = ppro.next;
            }
            return pro;
        }
    }
}
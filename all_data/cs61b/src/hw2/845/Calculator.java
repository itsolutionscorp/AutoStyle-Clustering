import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eL;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int x1 = x;
        int x2 = y;
        int a1 = 0;
        int carry = 0;
        for(int i = 0; i < 32; i++) {
            a1 = x1^x2;
            carry = (x1&x2)<<1;
            x1 = a1;
            x2 = carry;
        }
        int ans = a1|carry;
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
        int x1 = x;
        int y1 = y;
        while(y1!=0) {
            if((y1&1)!=0){ //checks to see if x should add itself based on y's given 1's
                ans = ans + x1;
            }
            y1 = y1>>>1; //inc y1 logically (arithmetic causes infinite loop)
            x1 = x1<<1; //doubles to apply to y-value
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
        if (eL == null) {
            eL = new EquationList(equation, result, null);
        }
        else {
            EquationList emptyL = new EquationList(eL.equation,eL.result,eL.next);
            eL.equation = equation;
            eL.result = result;
            eL.next = emptyL;
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
        EquationList Counting = eL;
        int count = 0;
        if(Counting!=null)
            count=1;
        while(Counting != null && Counting.next!=null) {
            count++;
            Counting = Counting.next;
        } 
        printHistory(count);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList toPrint = eL;
        for (int i = 0; i < n; i++) {
            System.out.println(toPrint.equation + " = " + toPrint.result);
            toPrint = toPrint.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eL.equation = eL.next.equation;
        eL.result = eL.next.result;
        eL.next = eL.next.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eL = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList emptyL = eL;
        int toReturn = 0;
        if(emptyL == null)
            return 0;
        while (emptyL != null) {
            toReturn = toReturn + emptyL.result;
            emptyL = emptyL.next;
        }
        return toReturn;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList emptyL = eL;
        int toReturn = 0;
        if(emptyL == null)
            return 1;
        int first = emptyL.result;
        if(emptyL.next == null)
            return first;
        emptyL = emptyL.next;
        toReturn = first*emptyL.result;
        while(emptyL.next != null) {
            emptyL = emptyL.next;
            toReturn = toReturn*emptyL.result;
        }
        return toReturn;
    }
}
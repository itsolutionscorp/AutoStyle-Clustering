import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList history;
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
        while (y!=0){
            int carry = x & y; // carry has common set bits of x and y
            x = x ^ y; // sum of bits of x and y where at least one of the bits is not set
            y = carry << 1; // carry is shifted by one so that adding it to x gives the required sum
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
        int n1=x;
        int n2=y;
        int result=0;
        while (n2 != 0){
            if((n2 & 1) != 0){
                result = add(result, n1);
            }
            n1 <<= 1;
            n2 >>>= 1;
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
        // YOUR CODE HERE
        EquationList prevHistory = history;
        history = new EquationList(equation, result, prevHistory);
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
        // EquationList copyHistory=history;
        // while (copyHistory.next!=null){
        //     System.out.println(copyHistory.equation+" = "+copyHistory.result);
        //     copyHistory = copyHistory.next;
        // }
        // int j=0;
        // while (history.next!=null){
        //     j++;
        //     history=history.next;
        // }
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
        // YOUR CODE HERE
        EquationList newHistory = history;
        int i=0;
        while ((i<n) && (newHistory!=null)){
            System.out.println(newHistory.equation+" = "+newHistory.result);
            newHistory=newHistory.next;
            i++;      
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        // if (history==null){
        //     history=null;
        // }
        history = history.next;

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (history!=null){
            history=history.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList otherHistory = history;
        int sum = 0;
        while (otherHistory!=null){
            sum+=otherHistory.result;
            otherHistory=otherHistory.next;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList otherHistory = history;
        int product = 1;
        while (otherHistory!=null){
            product*=otherHistory.result;
            otherHistory=otherHistory.next;
        }
        return product;
    }
}
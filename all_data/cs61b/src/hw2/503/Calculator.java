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
        // YOUR CODE HERE
        // String x = Integer.toBinaryString(x);
        // String y = Integer.toBinaryString(y);
        // int x_len = x.length();
        // int y_len = y.length();
        // int min_len = Math.min(x_len, y_len);
        // Array x = x.toCharArray();
        // Array y = y.toCharArray(); 
        // for (i = 0; i < min_len; i++) {
        //     if ((x[i] == 0) && (y[i] == 0)) {}
        // }

    int answer = x ^ y; // exclusive or is practically addition, except 1 + 1 == 0, with 1 leftover. 
    int leftover = (x & y) << 1; //takes the 1 + 1 term and moves its leftover 1 one to the left, ie carries the leftover over by 1. 
    if (leftover != 0) {
        return add(answer, leftover); //recursively adds the leftover term to the answer
    }
    return answer;
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
        if (x == 0) {return 0;}

        int answer = 0;
        for (int i = 0; i < Math.abs(x); i++) {
            answer = add(answer, y);
        }

        if (x < 0) { //make the answer negative
            answer = ~ answer;
            answer = add(answer, 1);
        }

        return answer;
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

    public LList hist;
    public void saveEquation(String equation, int result) {
        Strange_Bob y = new Strange_Bob(equation, result);
        hist = new LList(y, hist);
        // if (hist == null) {
        //     hist = z;
        //add this method to LList class
         // System.out.print(hist.head.equation);
         // System.out.print(hist.head.n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
       // System.out.print(hist.length());
        if (hist == null) return;
        printHistory(hist.length());
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int m) {
        LList pointer = hist;
        if (m == 0) return;
        if (hist == null) return;
        while (pointer != null && m > 0) {
            System.out.println(pointer.head.equation + " = " + pointer.head.n); //when adding a string to anything, java converts the second thing to a string automatically
            pointer = pointer.tail;
            m -= 1;} //when traverse is 0, begin printing
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (hist == null) return;
        if (hist.tail == null) {
            hist = null;
            return;
        }
        hist.remove_front(); 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
          LList pointer = hist;
        int total = 0;
        if (pointer == null) {return 0;}
        while (pointer != null) {
            total += pointer.head.n;
            pointer = pointer.tail;}
        return total;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        LList pointer = hist;
        int total = 1;
        if (pointer == null) {return 1;}
        while (pointer != null) {
            total *= pointer.head.n;
            pointer = pointer.tail;}
        return total;}
}
    // Make an object which has a first element, and a rest element which is null.
    // Change null to the new thing every time save equation is called. 


    // public void undoEquation() {

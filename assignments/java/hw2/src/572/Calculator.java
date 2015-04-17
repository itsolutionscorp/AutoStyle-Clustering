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
        //int sum = (x^y);
        //int shift = (x&y);
        //if (shift == 0) {
        //    return sum;
        if ((x&y) == 0) { // if there are no matching bits, ^ gives the sum
            return (x^y);
        }
        else { // if there are matching bits, ^ gives a temporary sum, and & gives the positions of the 1s that must be carried over
            //while (shift != 0) {
            //    int temp = (shift<<1);
            //    shift = sum&temp;
            //    sum = sum^temp;
            //}
            //return sum;
            return add(x^y, (x&y)<<1);
        }

        //return -1;
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
        if ((x==0)||(y==0)) {
            return 0;
        }
        int flip = 0;
        if ((x<0)^(y<0)) { // if exactly one is negative, the sign of the product must be flipped at the end
            flip = 1;
        }
        if (x<0) { // flips x if negative
            x = add(~x, 1);
        }
        if (y<0) { // flips y if negative
            y = add(~y, 1);
        }
        int temp = 0; // keeps track of extra terms to be added if y is not divisible by 2
        while (y!=1) {
            if ((y&1)==1) { // if last term in y is 1, takes 1 times x and stores it in a separate variable to be added later
                temp = add(temp, x);
            }
            x = x<<1; // multiply x by 2
            y = y>>1; // divide y by 2
        }
        int prod = add(x, temp); // adds the part of x that is a power of 2 and the remainder
        if (flip == 1) { // flips sign if set earlier
            prod = add(~prod, 1);
        }
        return prod;
        //return -1;
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

    EquationList history;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        //else {
        //    EquationList marker = history;
        //    while (history.next != null) {
        //        history = history.next;
        //    }
        //    history.next = new EquationList(equation, result, null);
        //    history = marker; 
        //}
        else {
            history = new EquationList(equation, result, history);
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
        // YOUR CODE HERE
        int i = 0;
        EquationList pointer = history;
        while (pointer != null) {
            pointer = pointer.next;
            i = i + 1;
        }
        printHistory(i);
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
        int i = 0;
        EquationList pointer = history;
        while ((i < n) && (pointer != null)) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
            i = i + 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        EquationList pointer = history;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        history = pointer.next;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int totalsum = 0;
        EquationList pointer = history;
        while (pointer != null) {
            totalsum = add(totalsum, pointer.result);
            pointer = pointer.next;
        }
        return totalsum;
        //return -1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int totalproduct = 1;
        EquationList pointer = history;
        while (pointer != null) {
            totalproduct = multiply(totalproduct, pointer.result);
            pointer = pointer.next;
        }
        return totalproduct;
        //return -1;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    //private int x;
    //private int y;
    private int s;
    private int p;
    public EquationList head = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) { //might need to remove the static on method
        // YOUR CODE HERE
/*        if (y != 0){
            return add( x^y, (x & y) << 1);
        }else {
            return x;
        } //good */
        //z = 0;
        while (x != 0) {
            int temp = y & x;
            y = y ^ x;
            x = temp << 1;
        }
        
        return y;
        //return x + y; //for testing multiply
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) { //might need to remove the static on method
        // YOUR CODE HERE
        int z = 0;
        if ((x > y) || (x == y)) {
            while (y != 0) {
                if ((y & 1) != 0) {
                    z = add(z, x);
                }
                x = x << 1;
                y = y >> 1;
            }
        }else{
            /*while (x != 0) {
                if ((x & 1) != 0) {
                    z = add(z, y);
                }
                y = y << 1;
                x = x >> 1;
            }*/
            return multiply(y , x);
        } //good

        return z;
        //return x * y; //for testing
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
        EquationList temp = new EquationList(equation, result, null);
        EquationList current = head;
        temp.next = current;
        head = temp; // good
        //temp = current;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE
        if (head == null) {
            //System.out.println("no history!");
        }
        EquationList temp = head;
        while (temp != null) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
        } //good
        
        //System.out.println(head.next.equation + " + " + head.next.result);
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
        if (head == null) {
            //System.out.println("no history!");
        } else {
            EquationList temp = head;
            int i = 0;
            while (i < n){
                if (temp != null) {
                    System.out.println(temp.equation + " = " + temp.result);
                    temp = temp.next;                   
                } else {
                    //int result = i+1;
                    //System.out.println("calling more than stored equations! slot " + result + " is empty");
                }
                i += 1;
            }
        }
        //while (int i < n) {
            //while (head != null) {
                //System.out.println(head.equation + " = " + head.result);
                //head = head.next;
            //}
        //}
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (head == null){
            //System.out.println("no history!");
        } else{
            head = head.next; 
        }

    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        if (head == null) {
            //System.out.println("no history!");
        }
        while (head != null) {
            head = head.next;
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
        s = 0;

        if (head == null) {
            //s = 0; //do nothing
        } else {
            EquationList temp = head;
            while (temp != null) {
                s += temp.result;
                temp = temp.next;
            }
        }

        return s; //done
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        p = 1;

        if (head == null) {
            //p = 1; //do nothing
        } else {
            EquationList temp = head;
            while (temp != null){
                p *= temp.result;
                temp = temp.next;
            }
        }

        return p; //done
    }
}
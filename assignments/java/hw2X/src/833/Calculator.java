import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList head = new EquationList("", 0, null);
    int length = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if ((x & y) == 0) {
            return x | y;
        }
        int overflow = x & y;
        overflow = overflow << 1;
        return add(x ^ y, overflow);
    }

    // Returns index of largest n such that exp(2, n) <= x
    public int index(int x) {
        int shift = 0;
        while ((x >>> shift) != 0) {
            shift = add(shift, 1);
        }
        return shift;
    }

    // Returns -x
    public int negate(int x) {
        if (x == 0) {
            return 0;
        }
        return add(~x, 1);
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
        if (x == 0 || y == 0) {
            return 0;
        }
        if (x < 0 && y < 0) {
            return multiply(negate(x), negate(y));
        }
        if (x < 0 && y > 0) {
            return negate(multiply(negate(x), y));
        }
        if (x > 0 && y < 0) {
            return negate(multiply(x, negate(y)));
        }
        if (y == 1) {
            return x;
        }
        int largestIndex = index(y);
        int largestNumber = 1 << add(largestIndex, negate(1));
        int remaining = add(y, negate(largestNumber));
        return (x << add(largestIndex, negate(1))) + multiply(x, remaining);
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
        EquationList newHead = new EquationList(equation, result, this.head);
        this.head = newHead;    
        this.length = this.length + 1;
    }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        this.printHistory(this.length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (n > this.length || n < 1) {
            // Throw error if user happens to be allowed to input such n
        } 
        EquationList pointer = this.head;
        for (int i = 1; i <= n; i++) {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (this.length == 0) {
        } else {
            this.head = this.head.next;
            this.length = this.length - 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (this.head.next != null){
            this.head = this.head.next;
        }
        this.length = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
       if (this.length == 0) {
        return 0;
       } 
       int runningTotal = 0;
       EquationList pointer = this.head;
       while (pointer.next != null) {
        runningTotal = runningTotal + pointer.result;
        pointer = pointer.next;
       }
       return runningTotal;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        if (this.length == 0) {
        return 1;
       } 
       int runningTotal = 1;
       EquationList pointer = this.head;
       while (pointer.next != null) {
        runningTotal = runningTotal * pointer.result;
        pointer = pointer.next;
       }
       return runningTotal;
    }
}






import list.EquationList;

public class Calculator {

    public EquationList history = null;
    public int historyLength = 0;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        //Solution inspired by simple full-adder circuit
        if(y == 0) {
            return x; //returns completed addition result when carry is 0
        }
        int sum = x ^ y; //right-hand sum of bits
        int carry = x & y; //left-hand sum of bits
        return add(sum, carry << 1); //shifts carry to the left for addition and recurses until carry is 0
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
        int product = 0;
        while(y != 0) { //repeats until y is zero or "exhausted"
            if((y & 1) != 0) {
                product = add(x, product); //increments product by x if the current bit of y is 1
            }
            x = x << 1; //multiplies x by 2 to preserve binary place value parity with y
            y = y >>> 1; //moves on to the next bit of y
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
        this.history = new EquationList(equation, result, this.history);
        this.historyLength++;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        this.printHistory(this.historyLength);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList point = this.history;
        if(n > this.historyLength) {
            n = this.historyLength; //avoiding NullPointerException
        }
        for(int i = 0; i < n; i++) {
            System.out.println(point.equation + " = " + Integer.toString(point.result));
            point = point.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(this.historyLength > 0) {
            //relying on Java garbage collection to discard what used to be this.history
            this.history = this.history.next;
            this.historyLength--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        this.history = null;
        this.historyLength = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList point = this.history;
        int result = 0;
        for(int i = 0; i < this.historyLength; i++) {
            result += point.result;
            point = point.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList point = this.history;
        int result = 1;
        for(int i = 0; i < this.historyLength; i++) {
            result *= point.result;
            point = point.next;
        }
        return result;
    }
}
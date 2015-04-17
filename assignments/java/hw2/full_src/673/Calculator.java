import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList list = null;
    
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if ((x & y) == 0)
            return x ^ y;
        return add(x ^ y, (x & y) << 1);
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
        if (y < 0 && x < 0) {
            y = -y;
            x = -x;
        }
        else if (y < 0) {
            int temp = x;
            x = y;
            y = temp;
        }
        int answer = 0;
        if (y == 0) {
            return 0;
        }
        else if (y == 1) {
            return x;
        }
        else {
            if (isPowerOfTwo(y)) {
                return x << logOfTwo(y);
            }
            else {
                answer = x << logOfTwo(y);
                answer = add(answer, multiply(x, (y - powerOfTwo(logOfTwo(y)))));
            }
        }
        return answer;
    }
    public boolean isPowerOfTwo(int n) {
        if ((add(n, -1) & n) == 0) {
            return true;
        }
        return false;
    }
    public int logOfTwo(int n) {
        //if n is a power of 2, get the logarithm.
        //if n is not a power of 2, get the logarithm of the nearest smaller power of 2.
        //ex: logOfTwo(24) = logOfTwo(16) = 4
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return logOfTwo(n >> 1) + 1;
    }
    public int powerOfTwo(int n) {
        int answer = 1;
        while (n > 0) {
            answer = answer << 1;
            n -= 1;
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
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        EquationList newEntry = new EquationList(equation, result, this.list);
        this.list = newEntry;
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
        this.printHistory(getEquationListLength(this.list));
    }
    public int getEquationListLength(EquationList list) {
        if (list == null) {
            return 0;
        }
        return getEquationListLength(list.next) + 1;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList pointer = this.list;
        // YOUR CODE HERE
        while (n > 0) {
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
            pointer = pointer.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        this.list = this.list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while (this.list != null) {
            undoEquation();
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
        int sum = 0;
        EquationList pointer = this.list;
        while (pointer != null) {
            sum += pointer.result;
            pointer = pointer.next;
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
        // YOUR CODE HERE
        int product = 1;
        EquationList pointer = this.list;
        while (pointer != null) {
            product *= pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}
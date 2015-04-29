import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null; 
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = 0;
        int newnumber = 0;
        for (int n = 0; n < 32; n++) {
            int xbit = getBit(x, n);
            int ybit = getBit(y, n);
            if ((xbit == 0 && ybit == 0) && carry == 0) {
                carry = 0;
            }
            else if ((xbit == 0 && ybit == 0) && carry == 1) {
                carry = 0;
                newnumber = setBit(newnumber, n);
            }
            else if (((xbit ^ ybit) == 1) && carry == 0) {
                carry = 0;
                newnumber = setBit(newnumber, n);
            }
            else if (((xbit ^ ybit) == 1) && carry == 1) {
                carry = 1;
            }
            else if (((xbit & ybit) == 1) && carry == 0) {
                carry = 1;
            }
            else if (((xbit & ybit) == 1) && carry == 1) {
                carry = 1;
                newnumber = setBit(newnumber, n);
            }
            else {
                carry = 1;
                newnumber = setBit(newnumber, n);
            }
        }
        return newnumber;
    }

    public int getBit(int x, int i) {
        int compare = 000001 << i;
        return (x & compare) >>> i; 
    }

    public int setBit(int x, int i) {
        int setnumber = 00001 << i;
        return (x | setnumber);
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
        int newnumber = 0;
        for (int n = 0; n < 32; n++) {
            if (getBit(x, n) == 1) {
                newnumber = (add((y << n), newnumber));
            }
        }
        return newnumber;
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
        if (history == null) {
            EquationList recenthistory =  new EquationList(equation, result, history);
            history = recenthistory;
        }
        else {
            EquationList recenthistory = new EquationList(equation, result, history);
            history = recenthistory;
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
        printHistory(listLength(history));
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
        int counter = 0;
        while (counter < n || counter < (listLength(history))) {
            System.out.println(history.equation + " = " + history.result);
            history = history.next;
            counter = counter + 1;
        }
    }    

    public int listLength(EquationList history) {
        int counter = 0;
        while (history != null) {
            history = history.next;
            counter = counter + 1;
        }
        return counter;
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
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int counter = 0; 
        int sum = 0; 
        while(counter < listLength(history)) {
            sum = sum + history.result;
            history = history.next;
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
        int counter = 0; 
        int product = 1; 
        while(counter < listLength(history)) {
            product = product * history.result;
            history = history.next;
        }
        return product; 
    }
}
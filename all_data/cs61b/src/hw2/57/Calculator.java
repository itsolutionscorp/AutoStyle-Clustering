import list.EquationList;

public class Calculator {
    EquationList origin, currentNode;
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
        int andVar = x&y;
        int orVar = x^y;
        if (andVar == 0) return orVar;
        andVar = andVar << 1;
        return add(andVar, orVar);
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
        if (y == 0 || x == 0) return 0;
        else if (((y >>> 1 << 1) ^ y) != 0) {
            return add(x, multiply(x << 1, y >> 1));
        }
        else {
            return multiply(x << 1, y >> 1);
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
        EquationList newNode = new EquationList(equation, result, null);
        if (currentNode == null) {
            origin = newNode;
            currentNode = newNode;
        }
        else {
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }

    }

    /* Custom class to calculate length of history. */
    public int length() {
        int historyLength = 0;
        EquationList nodeReference = origin;
        while (nodeReference != null) {
            historyLength += 1;
            nodeReference = nodeReference.next;
        }
        return historyLength;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(length());
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (origin == null) return;
        EquationList nodeReference = origin;
        String statement = "";
        for (int i=1;i<=n;i++) {
            if (nodeReference == null) break;
            statement = (nodeReference.equation + " = " + nodeReference.result) + statement;
            if (i != n) statement = "\n" + statement;
            nodeReference = nodeReference.next;
        }
        System.out.println(statement);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList nodeReference = origin;
        int newEnd = length();
        for (int i=2;i<newEnd;i++) {
            nodeReference = nodeReference.next;
        }
        nodeReference.next = null;
        currentNode = nodeReference;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        origin = null;
        currentNode = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int returnSum = 0;
        EquationList nodeReference = origin;
        while (nodeReference != null) {
            returnSum += nodeReference.result;
            nodeReference = nodeReference.next;
        }
        return returnSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int returnProduct = 1;
        EquationList nodeReference = origin;
        while (nodeReference != null) {
            returnProduct *= nodeReference.result;
            nodeReference = nodeReference.next;
        }
        return returnProduct;
    }
}
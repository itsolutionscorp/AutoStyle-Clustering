import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList datList = null;

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
        boolean verbatim = false;

        String xBin = Integer.toBinaryString(x);
        String yBin = Integer.toBinaryString(y);
        String carryBin;
        
        if (verbatim){System.out.println("*****New Problem*****");}
        if (verbatim){System.out.println("X is:");}
        if (verbatim){System.out.println(Integer.toBinaryString(x));}
        if (verbatim){System.out.println("Y is:");}
        if (verbatim){System.out.println(Integer.toBinaryString(y));}
        // First, let's make binary that will represent carrying.
        int prelimCarry = x & y;
        int prelimCarryShift = prelimCarry << 1;
        if (verbatim){System.out.println("Shifted original carry");}
        if (verbatim){System.out.println(Integer.toBinaryString(prelimCarryShift));}

        carryBin = Integer.toBinaryString(prelimCarryShift);
        int tempX;
        int tempXMerged;
        int tempY;
        int tempYMerged;
        int counter = carryBin.length();
        do {
            tempX = (prelimCarryShift & x) << 1;
            tempXMerged = tempX | prelimCarryShift;
            tempY = (prelimCarryShift & y) << 1;
            tempYMerged = tempY | prelimCarryShift;
            counter += 1;
            prelimCarryShift = tempXMerged | tempYMerged;
            if (verbatim){System.out.println("Carry:");}
            if (verbatim){System.out.println(Integer.toBinaryString(prelimCarryShift));}
        } while (counter <= Math.max(xBin.length(), yBin.length()) + 10);

        int finalCarry = prelimCarryShift;

        // Then, let's use the carry, x, and  y to do the addition.
        int intermediate = x ^ finalCarry;
        int result = intermediate ^ y;

        return result;
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
        int iterator;
        int addThingy;
        if (x == 0 || y == 0){
            return 0;
        } else if (x < 0 && y < 0){
            iterator = Math.abs(x);
            addThingy = Math.abs(y);
        } else if (x < 0 && y > 0){
            iterator = y;
            addThingy = x;
        } else if (x > 0 && y < 0){
            iterator = x;
            addThingy = y;
        } else {
            iterator = x;
            addThingy = y;
        }

        int runningResult = addThingy;
        while (iterator > 1){
            runningResult = add(runningResult, addThingy);
            iterator = iterator - 1;
        }
        return runningResult;
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
        datList = new EquationList(equation, result, this.datList);
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
        EquationList x = datList;
        while (x != null){
            System.out.println(x.equation + " = " + x.result);
            x = x.next;
        }
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
        EquationList x = datList;
        int counter = 0;
        while (counter < n && x != null){
            System.out.println(x.equation + " = " + x.result);
            x = x.next;
            counter += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        datList = datList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        datList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        // return -1;
        if (datList == null){
            return 0;
        }
        int datSum = 0;
        EquationList x = datList;
        while (x != null){
            datSum = add(datSum, x.result);
            x = x.next;
        }
        return datSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        // return -1;
        if (datList == null){
            return 1;
        }
        int datProd = 1;
        EquationList x = datList;
        while (x != null){
            datProd = multiply(datProd, x.result);
            x = x.next;
        }
        return datProd;
    }
}
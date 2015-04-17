import list.EquationList;

public class Calculator {
    EquationList history = null;
    EquationList historyNew = history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sumIndex = 0;
        int xEnd = 0;
        int yEnd = 0;
        int interval;
        int sum = 0;

        while (sumIndex < 32) {
            // Repeatedly check then shave off the last digit of the two numbers with >>>

            xEnd = ((x << 31) >>> 31);
            yEnd = ((y << 31) >>> 31);
            x = x >>> 1;
            y = y >>> 1;

            if (yEnd == 1 && xEnd == 1) {
                interval = 2;
            } else if ((xEnd == 1 || yEnd == 1) && ((sum >> sumIndex) == 1)) {
                interval = 3;
            } else {
                interval = xEnd ^ yEnd;
            }

            sum = (sum ^ (interval << sumIndex));

            sumIndex = sumIndex +1;
        }
        return sum;
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
    // Find the closest power of two, then add until you get to the number you are looking for
        int sum = 0;
        int big = 0;
        int small = 0;
        int smallCopy = 0;
        int twoPower = 1;
        int counter = 0;
        int addTimes = 0;

        //If we have two neg. numbers, can just change them to pos.
        if ((x < 0) && (y < 0)) {
            x = Math.abs(x);
            y = Math.abs(y);
        }

        //Decides which of the iputs is larger, so we know which one to count up to
        if (x > y) {
            big = x;
            small = y;
            smallCopy = y;
        } else {
            big = y;
            small = x;
            smallCopy = x;
        }

        /**Finds the closest power of two to the larger number
        /*Also finds the difference between the bigger number and the power of two 
        /*to see how many times we need to add the smaller number**/
        while (twoPower <= big) {
            small = small << 1;
            twoPower = twoPower << 1;
            addTimes = (twoPower >>> 1) ^ big; 
            sum = small >>> 1;
        }

        //Adds the smaller number to the closest power of two the correct number of times
        while (counter < addTimes) {
            sum = add(sum, smallCopy);
            counter += 1;
        }

        if (((x < 0) && (y > 0)) || ((x > 0) && (y < 0))) {
            return add(~add(sum ^ (Integer.MAX_VALUE), 1), 1);
        } else {
            return sum;
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
        history = new EquationList(equation, result, history);
        historyNew = history;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {

        if (historyNew == null) {
            historyNew = history;
            return;
        }
        System.out.println(historyNew.equation + " = " + historyNew.result);
        historyNew = historyNew.next;
        printAllHistory();
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (n == 0) {
            historyNew = history;
            return;
        }
        System.out.println(historyNew.equation + " = " + historyNew.result);
        historyNew = historyNew.next;
        printHistory(n - 1);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
        historyNew = history;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        historyNew = history;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int cumulNum = 0;

        if (historyNew == null)
            return 0;

        cumulNum += historyNew.result;
        historyNew = historyNew.next;
        cumulNum += cumulativeSum();
        historyNew = history;
        return cumulNum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int cumulNum = 1;

        if (historyNew == null)
            return 1;

        cumulNum *= historyNew.result;
        historyNew = historyNew.next;
        cumulNum *= cumulativeProduct();
        historyNew = history;
        return cumulNum;
    }
}
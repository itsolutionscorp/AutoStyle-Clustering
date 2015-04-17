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
        int sum = x, sumone = y, sumtwo = 0;
        while (sumone != 0) {
            sumtwo = sum & sumone;
            sum = sum ^ sumone;
            sumone = sumtwo << 1;
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
        // YOUR CODE HERE
        /*this is my original, not so pretty*/
        /* int xtwo = x;
        int z = y;
        int product = 0;
        if ((x == 0) || (y == 0)) {
            return 0;
        }
        if ((xtwo < 0) && (y < 0)) {
            xtwo = add(xtwo, -1);
            xtwo = ~xtwo;
        }
        while (z != 0) {
            product = add(product, xtwo);
            
            if (z < 0) {
                z += 1;
            } 
            else {
            z -= 1;
            }
        }
        if ((x > 0) && (y < 0)) {
            product = ~product;
            product = add(product, 1);
        } 
        return product;
    } 
*/
    /*this is my good version */
    if (x == 0) {
        return 0;
    }
    /*if ((x < 0) && (y < 0)) {
        x = ~ add(x, - 1);
        y = ~ add(y, - 1);
    }
    if ((x > 0) && (y < 0)) {
        x = add((~ x), 1);
        y = ~ add(y, - 1);
    } */
    int result = 0;
    while (y != 0) {
        if ((y & 1) == 1) {
            result = add(result, x);
            x = x << 1;
            y = y >>> 1;
        }
        else {
            x = x << 1;
            y = y >>> 1;
        }
    }
    return result;
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
    int history_size = 0;
    public EquationList history = new EquationList("example equation", 0, null);
    public EquationList historytwo = history;

    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
            history.next = new EquationList(history.equation, history.result, history.next);
            history.equation = equation;
            history.result = result;
            history_size += 1;
        //while (historytwo.next != null) {
        //    historytwo = historytwo.next;
        //}
        //historytwo.next = new EquationList(equation, result, null);
        //history_size += 1;
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
        printHistory(history_size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        // YOUR CODE HER
        // EquationList historyprinter = new EquationList("stuff", 0, null);
        EquationList historycounter = history;
        /* while (historycounter != null) {
            historyprinter.next = new EquationList(historyprinter.equation, historyprinter.result, historyprinter.next);
            historyprinter.equation = historycounter.equation;
            historyprinter.result = historycounter.result;
            historycounter = historycounter.next; 
        }
        if (n > history_size) {
                n = history_size;
            }
        while (n > 0) {
            System.out.println(historyprinter.equation + " = " + historyprinter.result);
            historyprinter = historyprinter.next;
            n -= 1;
        }
    }   */
    if (n > history_size) {
        n = history_size;
    }
        while (n > 0) {
            System.out.println(historycounter.equation + " = " + historycounter.result);
            if (historycounter.next != null) {
            historycounter = historycounter.next;
            }
            n -= 1;
        }
    }  
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
       // EquationList undohelper = history;
       // while (undohelper.next != null) {
       //     undohelper = undohelper.next;
       // }
       // undohelper = null;
       // history_size -= 1;
       history = history.next;
       history_size -= 1; 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = new EquationList("example equation", 0, null);
        history_size = 0;
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
        EquationList sumhelper = history;
        while (sumhelper.next != null ) {
            sum += sumhelper.result;
            sumhelper = sumhelper.next;
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
        EquationList producthelper = history;
        while (producthelper.next != null ) {
            product *= producthelper.result;
            producthelper = producthelper.next;
        }
        return product;
    }
}
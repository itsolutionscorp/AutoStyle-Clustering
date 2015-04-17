import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    Equationlist history = new Equationlist();
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0) {
            int carry = (x & y) ;
            x = x ^ y;
            y = carry << 1;
        }
        return x;
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
        int result = 0;
        while (y != 0) {
            if ((y & 01) != 0) {
                result = add(result, x); 
            }
            x <<= 1;
            y >>= 1;
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
    public void saveEquation(String equation, int result) {
        List last = null;
        /** EquationList.history[0] = String equation;
        EquationList.history[1] = int result;
        EquationList.history[2] = new array[saveEquation()]; **/
        history.add(equation, result, last);
        last = new saveEquation();

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        /** for (String i:EquationList.history) {
            system.out.println(EquationList.history[0] + "=" + EquationList.history[1]) **/
        /** if (EquationList.history.head != null) {
            system.out.println(EquationList.history[0] + "=" + EquationList.history[1])
            EquationList.history.head = EquationList.history.tail; **/
        if (n == 0) {
            printHistory(0);
        }
        else {
            printHistory(history.length);
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
        int counter = 1;
        if (counter != n) {
            system.out.println(history[0] + "=" + history[1]);
            history.head = history.tail;
            counter += 1;

        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        /** if (EquationList.history.head != null) {
            EquationList.history.head = EquationList.history.tail; **/
        history.remove(history.size - 1);
            
        }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        if (history != null) {
            history = null;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int total = 0;
        if (history.length == 0) {
            return total;
        }
        else for (int i:history) {
            if (i == (int)i) {
                total = total + i; 
            }
        }
             
        return total; }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int total = 1;
        if (history.length == 0) {
            return total;
        }
        else for (int i:history) {
            if (i == (int)i) {
                total = total * i; 
            }
        }
             
        
        return total; }
    }
    
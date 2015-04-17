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
    public EquationList history;
    public boolean condition = true;

    public int add(int x, int y) {
        int carry;
        int anded = x & y;
        int xored = x ^ y;
        while (anded != 0) {
            anded = anded << 1;
            carry = xored ^ anded;
            anded = anded & xored;
            xored = carry;
        }
        if (condition) {
                saveEquation((x + " + " + y), xored);
            }
        return xored;
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
        int yy = y;
        int xx = x;
        if (y < 0) {
            condition = false;
            y = add(1, ~y);
            x = add(1, ~x);
        }
        int temp = 0;
        while (y != 0 && x != 0) {
            condition = false;
            temp = add(temp, x);
            y = add(y, -1);
        }
        condition = true;
        saveEquation((xx + " * " + yy), temp);
        return temp;
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
        // if (condition) {
        //     entrycount += 1;
            history = new EquationList(equation, result, history);
        // }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList savedd = history;
        while (savedd != null) {
            System.out.println(savedd.equation + " = " + savedd.result);
            savedd = savedd.next;
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
        EquationList saved = history;
        while (saved.next != null && n > 0) {
            System.out.println(saved.equation + " = " + saved.result);
            saved = saved.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList tempo = history;
        int sum = 0;
        while (tempo.next != null) {
            sum = sum + tempo.result;
            tempo = tempo.next;
        }
        return sum + tempo.result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList tempa = history;
        int product = 1;
        while (tempa != null) {
            product = product * tempa.result;
            tempa = tempa.next;
        }
        return product;
    }
}
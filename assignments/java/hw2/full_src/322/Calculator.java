import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList eqList;

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
            int k = (x & y);
            x = x ^ y; 
            y = k << 1;
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
        int product = 0;   
             
        if ((x < 0) && (y < 0)) {
            x = add(~x, 1);
            y = add(~y, 1);
        }
        if (y < 0) {
            int n = y;
            y = x;
            x = n;
        }
        while (y != 0) {
            if ((y & 01) != 0) {
                product = add(product, x);
                y -= 1;
            }
            x <<= 1;
            y >>= 1;
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
        EquationList eqListNew = new EquationList(equation, result, eqList);
        eqList = eqListNew;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList lst = eqList;
        while (lst != null) {
            System.out.println(lst.equation + " = " + lst.result);
            lst = lst.next;
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
        EquationList lst = eqList;
        int k = 0;
        while (k < n) {
            k += 1;
            lst = lst.next;
        }
        System.out.println(lst.equation + " = " + lst.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqList = eqList.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList lst = eqList;
        int sums = 0;
        while (lst != null) {
            sums += lst.result;
            lst = lst.next;
        }
        return sums;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList lst = eqList;
        int products = 1;
        while (lst != null) {
            products *= lst.result;
            lst = lst.next;
        }
        return products;
    }
}
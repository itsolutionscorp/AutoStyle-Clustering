import list.EquationList;

public class Calculator {

    EquationList list = new EquationList ("", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    /*public int add(int x, int y) {
        int a = x ^ y;
        int b = (x & y) << 1;
        int c = a ^ b;

        while ((a & b)!=0) {
            a = a ^ b;
            b = (a & b) << 1;
            c = a ^ b;
        } 
        return c;
    }*/

    public int add(int x, int y) {
        int a = x ^ y;
        int b = x & y;

        while (b != 0) {
            b = b << 1;
            return add(a, b);
        }
        return a;
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
        int a = x, b = y, c = 0, d = 1;

        while (a != 0) {
            if ((b & d) == 1) {
                c = add(c, a);
            }
            a = a << 1;
            b = b >>> 1;
        }
        return c;
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
        EquationList history = new EquationList(equation, result, list);
        list = history;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList allHistory = list;
        
        while (allHistory.next != null) {
            System.out.println(allHistory.equation + " = " + allHistory.result);
            allHistory = allHistory.next;
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
        EquationList history2 = list;

        while ((n != 0) & (history2.next != null)) {
            System.out.println(history2.equation + " = " + history2.result);
            history2 = history2.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list.equation = list.next.equation;
        list.result = list.next.result;
        list.next = list.next.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        list = new EquationList("", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList sumList = list;

        while (sumList.next != null){
        sum = sum + sumList.result;
        sumList = sumList.next;
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
        int product = 1;
        EquationList productList = list;

        while (productList.next != null){
        product = product * productList.result;
        productList = productList.next;
        }

        return product;
    }
}
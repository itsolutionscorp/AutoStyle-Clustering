import list.EquationList;

public class Calculator {
    public EquationList list = null;
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
        int count = 0;
        int result = x;
        while (y != 0) {
            int temp = x & y;
            x = x ^ y;
            y = temp << 1;
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
        int a = x;
        int b = y;
        if (a < 0) {
            a = add(~a, 1);
        }
        if (b < 0) {
            b = add(~b, 1);
        }
        while (b != 0) {
            int temp_y = b;
            temp_y = temp_y & 1;
            int temp_x = a;
            if (temp_y == 1) {
                while (temp_x > 0) {
                    temp_y = temp_y << 1;
                    temp_y = temp_y | 1;
                    temp_x = temp_x >> 1;
                }
                int temp = a & temp_y;
                result = add(result, temp);
            }
            a = a << 1;
            b = b >> 1;
        }
        if ((x < 0 && y > 0) || (x > 0 && y < 0)) {
            return add(~result, 1);
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
        list = new EquationList(equation, result, list);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList list1 = list;
        int n = 0;
        while (list1 != null) {
            n += 1;
            list1 = list1.next;
        }
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList list1 = list;
        while (n > 0) {
            System.out.println(list1.equation + " = " + list1.result);
            list1 = list1.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        list = list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList list1 = list;
        int sum = 0;
        while (list1 != null) {
            sum += list1.result;
            list1 = list1.next;
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
        EquationList list1 = list;
        int product = 1;
        while (list1 != null) {
            product *= list1.result;
            list1 = list1.next;
        }
        return product;
    }
}
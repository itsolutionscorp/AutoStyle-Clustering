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
        int temp1 = x ^ y;
        int temp2 = x & y;
        temp2 = temp2 << 1;
        int temp3;
        int index = 0;
        while (index < 31) {
            temp3 = temp1 ^ temp2;
            temp2 = temp1 & temp2;
            temp2 = temp2 << 1;
            temp1 = temp3;
            index += 1;
        }
        return temp1;
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
        int result;
        if (y != 0) {
            int counter = 0;
            int temp = y; 
            int power_position = 1 & temp;
            while (power_position != 1) {
                temp = temp >>> 1;
                power_position = 1 & temp;
                counter += 1;
            }
            int max_power = x << counter;
            int erase = 1 << counter;
            y = y ^ erase;
            result = add(max_power, multiply(x, y));
        } else {
            result = 0;
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
    public EquationList savedEq = new EquationList("First", 0, null);
    public void saveEquation(String equation, int result) {
        savedEq = new EquationList(equation, result, savedEq);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp_list = savedEq;
        while (temp_list.next != null) {
           System.out.println(temp_list.equation + " = " + temp_list.result);
           temp_list = temp_list.next; 
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
        EquationList temp_list = savedEq;
        while (n > 0 && temp_list.next != null) {
           System.out.println(temp_list.equation + " = " + temp_list.result);
           temp_list = temp_list.next;
           n = n - 1; 
        }    
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        savedEq = savedEq.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        savedEq = new EquationList("First", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int result = 0;
        EquationList temp_list = savedEq;
        while (temp_list.next != null) {
            result += temp_list.result;
            temp_list = temp_list.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int result = 1;
        EquationList temp_list = savedEq;
        while (temp_list.next != null) {
            result *= temp_list.result;
            temp_list = temp_list.next;
        }
        return result;
    }
}
import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList head;

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
            int temp = (x & y);
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
        int temp = 0;
        while (y != 0){
            int rand = (y & 01);
            if (rand > 0) {
                temp = add(temp, x);
            }
            x = x << 1;
            y = y >>> 1;
        }
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
        if (head == null) {
            head = new EquationList(equation, result, null);
        }
        else {
            head = new EquationList(equation, result, head);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList cur = head;
        if (cur == null){
            return;
        }
        if (cur.next == null){
            System.out.println(cur.equation + " = " + cur.result);
        }
        else {
            while (cur.next != null) {
                System.out.println(cur.equation + " = " + cur.result);
                cur = cur.next;
            }
            System.out.println(cur.equation + " = " + cur.result);
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
        EquationList cur1 = head;
        if (cur1 == null) {
            return;
        }
        while (n > 0 && cur1.next != null) {
            System.out.println(cur1.equation + " = " + cur1.result);
            cur1 = cur1.next;
            n -= 1;
        }
        if (n > 0){
            System.out.println(cur1.equation + " = " + cur1.result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList cur2 = head;
        head = cur2.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        head = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList cur3 = head;
        if (cur3 == null) {
            return 0;
        }
        else {
            while (cur3.next != null) {
                sum = add(sum, cur3.result);
                cur3 = cur3.next;
            }
            sum = add(sum, cur3.result);
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
        int total = 1;
        EquationList cur4 = head;
        if (cur4 == null) {
            return 1;
        }
        else {
            while (cur4.next != null) {
                total = multiply(total, cur4.result);
                cur4 = cur4.next;
            }
            total = multiply(total, cur4.result);
        }
        return total;
    }
}
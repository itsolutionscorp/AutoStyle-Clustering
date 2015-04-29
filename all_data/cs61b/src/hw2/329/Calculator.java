import list.EquationList;

public class Calculator {
    EquationList L;
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
        int same = x & y; // need to carry over bits to next place value that are same, essentially same as increasing power of 2 by 1
        int diff = x ^ y;
        int move = same << 1;
        if (same == 0) {
            return diff;
        } return add(diff, move);
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
        if (y == 0) {
            return 0;
        }

        int prod = 0;
        int i = 0;
        while (i < 32) {
            int remain = y & 1;
            if (remain == 1) {
                int newx = x << i;
                prod = add(prod,newx);               
            }
            i = i + 1;
            y = y >> 1;
        }

        return prod;
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
        EquationList neweq = new EquationList(equation, result, null);
        if (L == null) {
            L = new EquationList(equation,result,null);
        }
        L = new EquationList(neweq.equation,neweq.result,L);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int num = 0;
        EquationList pointer = L;
        while (pointer != null) {
            num = num + 1;
            pointer = pointer.next;
       }
       printHistory(num - 1);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
       int count = 0;
       EquationList pointer = L;
       EquationList pointer2 = L;

       int  num = 0; // number of equations in L
       while (pointer2 != null) {
            num = num + 1;
            pointer2 = pointer2.next;
       }

        while (count < n && count < (num - 1) && pointer != null) {
                count = count + 1;
                System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
                pointer = pointer.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList pointer = L;
        L = L.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        L = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        if (L == null) {
            return 0;
        }
        int num = 0;
        EquationList pointer = L;
        while (pointer != null) {
            num = num + 1;
            pointer = pointer.next;
       }
       int i = 0;
       int sum = 0;
       EquationList pointer2 = L;
       while (i < (num -1) && pointer2 != null) {
            sum = add(sum,pointer2.result);
            pointer2 = pointer2.next;
            i = i + 1;
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
        if (L == null) {
            return 1;
        }
        int num = 0;
        EquationList pointer = L;
        while (pointer != null) {
            num = num + 1;
            pointer = pointer.next;
       }
       int i = 0;
       int prod = 1;
       EquationList pointer2 = L;
       while (i < (num -1) && pointer2 != null) {
            prod = multiply(prod,pointer2.result);
            pointer2 = pointer2.next;
            i = i + 1;
       }
       return prod;
    } 
}